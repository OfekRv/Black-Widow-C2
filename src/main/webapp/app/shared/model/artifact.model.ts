import { Moment } from 'moment';
import { IAgent } from 'app/shared/model/agent.model';

export interface IArtifact {
  id?: number;
  receiveTime?: string;
  content?: string;
  agents?: IAgent[];
  agent?: IAgent;
}

export const defaultValue: Readonly<IArtifact> = {};
