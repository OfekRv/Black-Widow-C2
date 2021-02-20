import { ICommand } from 'app/shared/model/command.model';

export interface ICommandType {
  id?: number;
  name?: string;
  types?: ICommand[];
}

export const defaultValue: Readonly<ICommandType> = {};
