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

import { ICommandType, defaultValue } from 'app/shared/model/command-type.model';

export const ACTION_TYPES = {
  FETCH_COMMANDTYPE_LIST: 'commandType/FETCH_COMMANDTYPE_LIST',
  FETCH_COMMANDTYPE: 'commandType/FETCH_COMMANDTYPE',
  CREATE_COMMANDTYPE: 'commandType/CREATE_COMMANDTYPE',
  UPDATE_COMMANDTYPE: 'commandType/UPDATE_COMMANDTYPE',
  DELETE_COMMANDTYPE: 'commandType/DELETE_COMMANDTYPE',
  RESET: 'commandType/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICommandType>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type CommandTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: CommandTypeState = initialState, action): CommandTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_COMMANDTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_COMMANDTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_COMMANDTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_COMMANDTYPE):
    case REQUEST(ACTION_TYPES.DELETE_COMMANDTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_COMMANDTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_COMMANDTYPE):
    case FAILURE(ACTION_TYPES.CREATE_COMMANDTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_COMMANDTYPE):
    case FAILURE(ACTION_TYPES.DELETE_COMMANDTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_COMMANDTYPE_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_COMMANDTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_COMMANDTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_COMMANDTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_COMMANDTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/command-types';

// Actions

export const getEntities: ICrudGetAllAction<ICommandType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_COMMANDTYPE_LIST,
    payload: axios.get<ICommandType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<ICommandType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_COMMANDTYPE,
    payload: axios.get<ICommandType>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ICommandType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_COMMANDTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const updateEntity: ICrudPutAction<ICommandType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_COMMANDTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICommandType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_COMMANDTYPE,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
