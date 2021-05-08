import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ArchivedAsyncArtifact from './archived-async-artifact';
import ArchivedAsyncArtifactDetail from './archived-async-artifact-detail';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ArchivedAsyncArtifactDetail} />
      <ErrorBoundaryRoute path={match.url} component={ArchivedAsyncArtifact} />
    </Switch>
  </>
);

export default Routes;
