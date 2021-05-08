import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './archived-async-artifact.reducer';
import { IArchivedAsyncArtifact } from 'app/shared/model/archived-async-artifact.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IArchivedAsyncArtifactDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ArchivedAsyncArtifactDetail = (props: IArchivedAsyncArtifactDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { archivedAsyncArtifactEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          ArchivedAsyncArtifact [<b>{archivedAsyncArtifactEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="messageId">Message Id</span>
          </dt>
          <dd>{archivedAsyncArtifactEntity.messageId}</dd>
          <dt>
            <span id="consumeTime">Consume Time</span>
          </dt>
          <dd>
            {archivedAsyncArtifactEntity.consumeTime ? (
              <TextFormat value={archivedAsyncArtifactEntity.consumeTime} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/archived-async-artifact" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/archived-async-artifact/${archivedAsyncArtifactEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ archivedAsyncArtifact }: IRootState) => ({
  archivedAsyncArtifactEntity: archivedAsyncArtifact.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ArchivedAsyncArtifactDetail);
