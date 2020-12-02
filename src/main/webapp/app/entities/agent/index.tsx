import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Agent from './agent';
import AgentDetail from './agent-detail';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AgentDetail} />
      <ErrorBoundaryRoute path={match.url} component={Agent} />
    </Switch>
  </>
);

export default Routes;
