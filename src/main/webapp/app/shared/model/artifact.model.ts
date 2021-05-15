import { Moment } from 'moment';
import { IAgent } from 'app/shared/model/agent.model';

export interface IArtifact {
  id?: number;
  content?: string;
  consumeTime?: string;
  sentTime?: string;
  agent?: IAgent;
}

export const defaultValue: Readonly<IArtifact> = {};
