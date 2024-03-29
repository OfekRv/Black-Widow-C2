import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Agent from './agent';
import Command from './command';
import Artifact from './artifact';
import CommandType from './command-type';
import ArchivedAsyncArtifact from './archived-async-artifact';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}agent`} component={Agent} />
      <ErrorBoundaryRoute path={`${match.url}command`} component={Command} />
      <ErrorBoundaryRoute path={`${match.url}artifact`} component={Artifact} />
      <ErrorBoundaryRoute path={`${match.url}command-type`} component={CommandType} />
      <ErrorBoundaryRoute path={`${match.url}archived-async-artifact`} component={ArchivedAsyncArtifact} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
