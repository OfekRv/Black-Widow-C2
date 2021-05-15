import { Moment } from 'moment';

export interface IArchivedAsyncArtifact {
  id?: number;
  messageId?: string;
  consumeTime?: string;
  sentTime?: string;
}

export const defaultValue: Readonly<IArchivedAsyncArtifact> = {};
