import axios from 'axios';
import {
  parseHeaderForLinks,
  loadMoreDataWhenScrolled,
  ICrudGetAction,
  ICrudGetAllAction,
  ICrudPutAction,
  ICrudDeleteAction,
} from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAgent, defaultValue } from 'app/shared/model/agent.model';

export const ACTION_TYPES = {
  FETCH_AGENT_LIST: 'agent/FETCH_AGENT_LIST',
  FETCH_AGENT: 'agent/FETCH_AGENT',
  RESET: 'agent/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAgent>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type AgentState = Readonly<typeof initialState>;

// Reducer

export default (state: AgentState = initialState, action): AgentState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_AGENT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_AGENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_AGENT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_AGENT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_AGENT_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_AGENT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/agents';

// Actions

export const getEntities: ICrudGetAllAction<IAgent> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_AGENT_LIST,
    payload: axios.get<IAgent>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IAgent> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_AGENT,
    payload: axios.get<IAgent>(requestUrl),
  };
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
