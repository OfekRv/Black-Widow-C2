import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CommandType from './command-type';
import CommandTypeDetail from './command-type-detail';
import CommandTypeUpdate from './command-type-update';
import CommandTypeDeleteDialog from './command-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CommandTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CommandTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CommandTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={CommandType} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CommandTypeDeleteDialog} />
  </>
);

export default Routes;
