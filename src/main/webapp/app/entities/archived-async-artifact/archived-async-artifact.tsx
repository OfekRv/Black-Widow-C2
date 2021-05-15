import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './archived-async-artifact.reducer';
import { IArchivedAsyncArtifact } from 'app/shared/model/archived-async-artifact.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IArchivedAsyncArtifactProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ArchivedAsyncArtifact = (props: IArchivedAsyncArtifactProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { archivedAsyncArtifactList, match, loading } = props;
  return (
    <div>
      <h2 id="archived-async-artifact-heading">Archived Async Artifacts</h2>
      <div className="table-responsive">
        {archivedAsyncArtifactList && archivedAsyncArtifactList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Message Id</th>
                <th>Consume Time</th>
                <th>Sent Time</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {archivedAsyncArtifactList.map((archivedAsyncArtifact, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${archivedAsyncArtifact.id}`} color="link" size="sm">
                      {archivedAsyncArtifact.id}
                    </Button>
                  </td>
                  <td>{archivedAsyncArtifact.messageId}</td>
                  <td>
                    {archivedAsyncArtifact.consumeTime ? (
                      <TextFormat type="date" value={archivedAsyncArtifact.consumeTime} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {archivedAsyncArtifact.sentTime ? (
                      <TextFormat type="date" value={archivedAsyncArtifact.sentTime} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${archivedAsyncArtifact.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Archived Async Artifacts found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ archivedAsyncArtifact }: IRootState) => ({
  archivedAsyncArtifactList: archivedAsyncArtifact.entities,
  loading: archivedAsyncArtifact.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ArchivedAsyncArtifact);
