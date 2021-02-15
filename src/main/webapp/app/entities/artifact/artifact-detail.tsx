import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './artifact.reducer';
import { IArtifact } from 'app/shared/model/artifact.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IArtifactDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ArtifactDetail = (props: IArtifactDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { artifactEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Artifact [<b>{artifactEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="receiveTime">Receive Time</span>
          </dt>
          <dd>
            {artifactEntity.receiveTime ? <TextFormat value={artifactEntity.receiveTime} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="content">Content</span>
          </dt>
          <dd>{artifactEntity.content}</dd>
          <dt>Agent</dt>
          <dd>{artifactEntity.agent ? artifactEntity.agent.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/artifact" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/artifact/${artifactEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ artifact }: IRootState) => ({
  artifactEntity: artifact.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ArtifactDetail);
