import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import agent, {
  AgentState
} from 'app/entities/agent/agent.reducer';
// prettier-ignore
import command, {
  CommandState
} from 'app/entities/command/command.reducer';
// prettier-ignore
import artifact, {
  ArtifactState
} from 'app/entities/artifact/artifact.reducer';
// prettier-ignore
import commandType, {
  CommandTypeState
} from 'app/entities/command-type/command-type.reducer';
// prettier-ignore
import archivedAsyncArtifact, {
  ArchivedAsyncArtifactState
} from 'app/entities/archived-async-artifact/archived-async-artifact.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly agent: AgentState;
  readonly command: CommandState;
  readonly artifact: ArtifactState;
  readonly commandType: CommandTypeState;
  readonly archivedAsyncArtifact: ArchivedAsyncArtifactState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  agent,
  command,
  artifact,
  commandType,
  archivedAsyncArtifact,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
});

export default rootReducer;
