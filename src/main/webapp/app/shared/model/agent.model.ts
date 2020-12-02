import { Moment } from 'moment';
import { ICommand } from 'app/shared/model/command.model';

export interface IAgent {
  id?: number;
  ip?: string;
  lastActive?: string;
  commands?: ICommand[];
}

export const defaultValue: Readonly<IAgent> = {};
