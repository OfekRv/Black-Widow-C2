import { Moment } from 'moment';
import { IAgent } from 'app/shared/model/agent.model';
import { CommandStatus } from 'app/shared/model/enumerations/command-status.model';
import { CommandType } from 'app/shared/model/enumerations/command-type.model';

export interface ICommand {
  id?: number;
  sendTime?: string;
  status?: CommandStatus;
  type?: CommandType;
  agent?: IAgent;
}

export const defaultValue: Readonly<ICommand> = {};
