import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICommand, defaultValue } from 'app/shared/model/command.model';

export const ACTION_TYPES = {
  FETCH_COMMAND_LIST: 'command/FETCH_COMMAND_LIST',
  FETCH_COMMAND: 'command/FETCH_COMMAND',
  CREATE_COMMAND: 'command/CREATE_COMMAND',
  UPDATE_COMMAND: 'command/UPDATE_COMMAND',
  DELETE_COMMAND: 'command/DELETE_COMMAND',
  RESET: 'command/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICommand>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type CommandState = Readonly<typeof initialState>;

// Reducer

export default (state: CommandState = initialState, action): CommandState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_COMMAND_LIST):
    case REQUEST(ACTION_TYPES.FETCH_COMMAND):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_COMMAND):
    case REQUEST(ACTION_TYPES.UPDATE_COMMAND):
    case REQUEST(ACTION_TYPES.DELETE_COMMAND):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_COMMAND_LIST):
    case FAILURE(ACTION_TYPES.FETCH_COMMAND):
    case FAILURE(ACTION_TYPES.CREATE_COMMAND):
    case FAILURE(ACTION_TYPES.UPDATE_COMMAND):
    case FAILURE(ACTION_TYPES.DELETE_COMMAND):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_COMMAND_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_COMMAND):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_COMMAND):
    case SUCCESS(ACTION_TYPES.UPDATE_COMMAND):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_COMMAND):
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

const apiUrl = 'api/commands';

// Actions

export const getEntities: ICrudGetAllAction<ICommand> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_COMMAND_LIST,
  payload: axios.get<ICommand>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ICommand> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_COMMAND,
    payload: axios.get<ICommand>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ICommand> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_COMMAND,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICommand> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_COMMAND,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICommand> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_COMMAND,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
