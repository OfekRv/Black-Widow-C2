import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICommandType } from 'app/shared/model/command-type.model';
import { getEntities as getCommandTypes } from 'app/entities/command-type/command-type.reducer';
import { IAgent } from 'app/shared/model/agent.model';
import { getEntities as getAgents } from 'app/entities/agent/agent.reducer';
import { getEntity, updateEntity, createEntity, reset } from './command.reducer';
import { ICommand } from 'app/shared/model/command.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICommandUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CommandUpdate = (props: ICommandUpdateProps) => {
  const [typeId, setTypeId] = useState('0');
  const [agentId, setAgentId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { commandEntity, commandTypes, agents, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/command');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCommandTypes();
    props.getAgents();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...commandEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="blackWidowC2App.command.home.createOrEditLabel">Create Command</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : commandEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="command-id">ID</Label>
                  <AvInput id="command-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label for="command-type">Type</Label>
                <AvInput
                  id="command-type"
                  type="select"
                  className="form-control"
                  name="type.id"
                  value={isNew ? commandTypes[0] && commandTypes[0].id : commandEntity.type?.id}
                  required
                >
                  {commandTypes
                    ? commandTypes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
                <AvFeedback>This field is required.</AvFeedback>
              </AvGroup>
              <AvGroup>
                <Label for="command-agent">Agent</Label>
                <AvInput
                  id="command-agent"
                  type="select"
                  className="form-control"
                  name="agent.id"
                  value={isNew ? agents[0] && agents[0].id : commandEntity.agent?.id}
                  required
                >
                  {agents
                    ? agents.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
                <AvFeedback>This field is required.</AvFeedback>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/command" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  commandTypes: storeState.commandType.entities,
  agents: storeState.agent.entities,
  commandEntity: storeState.command.entity,
  loading: storeState.command.loading,
  updating: storeState.command.updating,
  updateSuccess: storeState.command.updateSuccess,
});

const mapDispatchToProps = {
  getCommandTypes,
  getAgents,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CommandUpdate);
