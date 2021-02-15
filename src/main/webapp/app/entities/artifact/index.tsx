import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Artifact from './artifact';
import ArtifactDetail from './artifact-detail';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ArtifactDetail} />
      <ErrorBoundaryRoute path={match.url} component={Artifact} />
    </Switch>
  </>
);

export default Routes;
