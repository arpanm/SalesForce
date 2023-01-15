import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './incentive.reducer';

export const IncentiveDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const incentiveEntity = useAppSelector(state => state.incentive.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="incentiveDetailsHeading">
          <Translate contentKey="salesForceApp.incentive.detail.title">Incentive</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{incentiveEntity.id}</dd>
          <dt>
            <span id="achivementPercent">
              <Translate contentKey="salesForceApp.incentive.achivementPercent">Achivement Percent</Translate>
            </span>
          </dt>
          <dd>{incentiveEntity.achivementPercent}</dd>
          <dt>
            <span id="incentiveValue">
              <Translate contentKey="salesForceApp.incentive.incentiveValue">Incentive Value</Translate>
            </span>
          </dt>
          <dd>{incentiveEntity.incentiveValue}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="salesForceApp.incentive.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{incentiveEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="salesForceApp.incentive.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {incentiveEntity.createdAt ? <TextFormat value={incentiveEntity.createdAt} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="salesForceApp.incentive.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {incentiveEntity.updatedAt ? <TextFormat value={incentiveEntity.updatedAt} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="salesForceApp.incentive.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{incentiveEntity.createdBy}</dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="salesForceApp.incentive.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{incentiveEntity.updatedBy}</dd>
          <dt>
            <Translate contentKey="salesForceApp.incentive.scheme">Scheme</Translate>
          </dt>
          <dd>{incentiveEntity.scheme ? incentiveEntity.scheme.id : ''}</dd>
          <dt>
            <Translate contentKey="salesForceApp.incentive.salesAgent">Sales Agent</Translate>
          </dt>
          <dd>{incentiveEntity.salesAgent ? incentiveEntity.salesAgent.id : ''}</dd>
          <dt>
            <Translate contentKey="salesForceApp.incentive.approvedBy">Approved By</Translate>
          </dt>
          <dd>{incentiveEntity.approvedBy ? incentiveEntity.approvedBy.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/incentive" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/incentive/${incentiveEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default IncentiveDetail;
