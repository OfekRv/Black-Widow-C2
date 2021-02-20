import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './command.reducer';
import { ICommand } from 'app/shared/model/command.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICommandProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Command = (props: ICommandProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { commandList, match, loading } = props;
  return (
    <div>
      <h2 id="command-heading">
        Commands
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Command
        </Link>
      </h2>
      <div className="table-responsive">
        {commandList && commandList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Send Time</th>
                <th>Status</th>
                <th>Type</th>
                <th>Agent</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {commandList.map((command, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${command.id}`} color="link" size="sm">
                      {command.id}
                    </Button>
                  </td>
                  <td>{command.sendTime ? <TextFormat type="date" value={command.sendTime} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{command.status}</td>
                  <td>{command.type ? <Link to={`command-type/${command.type.id}`}>{command.type.name}</Link> : ''}</td>
                  <td>{command.agent ? <Link to={`agent/${command.agent.id}`}>{command.agent.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${command.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${command.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${command.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Commands found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ command }: IRootState) => ({
  commandList: command.entities,
  loading: command.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Command);
