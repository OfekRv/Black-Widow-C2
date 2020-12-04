import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './agent.reducer';
import { IAgent } from 'app/shared/model/agent.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAgentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AgentDetail = (props: IAgentDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { agentEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Agent [<b>{agentEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="ip">Ip</span>
          </dt>
          <dd>{agentEntity.ip}</dd>
          <dt>
            <span id="lastActive">Last Active</span>
          </dt>
          <dd>{agentEntity.lastActive ? <TextFormat value={agentEntity.lastActive} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
        </dl>
        <Button tag={Link} to="/agent" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ agent }: IRootState) => ({
  agentEntity: agent.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AgentDetail);
