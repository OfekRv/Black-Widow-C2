import { Moment } from 'moment';
import { ICommandType } from 'app/shared/model/command-type.model';
import { IAgent } from 'app/shared/model/agent.model';
import { CommandStatus } from 'app/shared/model/enumerations/command-status.model';

export interface ICommand {
  id?: number;
  sendTime?: string;
  status?: CommandStatus;
  type?: ICommandType;
  agent?: IAgent;
}

export const defaultValue: Readonly<ICommand> = {};
