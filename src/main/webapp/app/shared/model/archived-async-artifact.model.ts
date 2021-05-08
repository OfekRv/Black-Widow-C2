import { Moment } from 'moment';

export interface IArchivedAsyncArtifact {
  id?: number;
  messageId?: string;
  consumeTime?: string;
}

export const defaultValue: Readonly<IArchivedAsyncArtifact> = {};
