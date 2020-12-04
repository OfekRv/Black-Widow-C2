import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './command.reducer';
import { ICommand } from 'app/shared/model/command.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICommandDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CommandDetail = (props: ICommandDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { commandEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Command [<b>{commandEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="sendTime">Send Time</span>
          </dt>
          <dd>{commandEntity.sendTime ? <TextFormat value={commandEntity.sendTime} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{commandEntity.status}</dd>
          <dt>
            <span id="type">Type</span>
          </dt>
          <dd>{commandEntity.type}</dd>
          <dt>Agent</dt>
          <dd>{commandEntity.agent ? commandEntity.agent.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/command" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ command }: IRootState) => ({
  commandEntity: command.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CommandDetail);
