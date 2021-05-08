import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IArchivedAsyncArtifact, defaultValue } from 'app/shared/model/archived-async-artifact.model';

export const ACTION_TYPES = {
  FETCH_ARCHIVEDASYNCARTIFACT_LIST: 'archivedAsyncArtifact/FETCH_ARCHIVEDASYNCARTIFACT_LIST',
  FETCH_ARCHIVEDASYNCARTIFACT: 'archivedAsyncArtifact/FETCH_ARCHIVEDASYNCARTIFACT',
  RESET: 'archivedAsyncArtifact/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IArchivedAsyncArtifact>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ArchivedAsyncArtifactState = Readonly<typeof initialState>;

// Reducer

export default (state: ArchivedAsyncArtifactState = initialState, action): ArchivedAsyncArtifactState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ARCHIVEDASYNCARTIFACT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ARCHIVEDASYNCARTIFACT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ARCHIVEDASYNCARTIFACT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ARCHIVEDASYNCARTIFACT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ARCHIVEDASYNCARTIFACT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ARCHIVEDASYNCARTIFACT):
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

const apiUrl = 'api/archived-async-artifacts';

// Actions

export const getEntities: ICrudGetAllAction<IArchivedAsyncArtifact> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ARCHIVEDASYNCARTIFACT_LIST,
  payload: axios.get<IArchivedAsyncArtifact>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IArchivedAsyncArtifact> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ARCHIVEDASYNCARTIFACT,
    payload: axios.get<IArchivedAsyncArtifact>(requestUrl),
  };
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
