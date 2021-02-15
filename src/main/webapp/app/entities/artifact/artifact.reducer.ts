import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IArtifact, defaultValue } from 'app/shared/model/artifact.model';

export const ACTION_TYPES = {
  FETCH_ARTIFACT_LIST: 'artifact/FETCH_ARTIFACT_LIST',
  FETCH_ARTIFACT: 'artifact/FETCH_ARTIFACT',
  RESET: 'artifact/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IArtifact>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type ArtifactState = Readonly<typeof initialState>;

// Reducer

export default (state: ArtifactState = initialState, action): ArtifactState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ARTIFACT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ARTIFACT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ARTIFACT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ARTIFACT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ARTIFACT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_ARTIFACT):
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

const apiUrl = 'api/artifacts';

// Actions

export const getEntities: ICrudGetAllAction<IArtifact> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ARTIFACT_LIST,
    payload: axios.get<IArtifact>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IArtifact> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ARTIFACT,
    payload: axios.get<IArtifact>(requestUrl),
  };
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
