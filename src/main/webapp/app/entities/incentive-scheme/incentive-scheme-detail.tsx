import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './incentive-scheme.reducer';

export const IncentiveSchemeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const incentiveSchemeEntity = useAppSelector(state => state.incentiveScheme.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="incentiveSchemeDetailsHeading">
          <Translate contentKey="salesForceApp.incentiveScheme.detail.title">IncentiveScheme</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{incentiveSchemeEntity.id}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="salesForceApp.incentiveScheme.type">Type</Translate>
            </span>
          </dt>
          <dd>{incentiveSchemeEntity.type}</dd>
          <dt>
            <span id="minAchivementPercent">
              <Translate contentKey="salesForceApp.incentiveScheme.minAchivementPercent">Min Achivement Percent</Translate>
            </span>
          </dt>
          <dd>{incentiveSchemeEntity.minAchivementPercent}</dd>
          <dt>
            <span id="maxAchivementPercent">
              <Translate contentKey="salesForceApp.incentiveScheme.maxAchivementPercent">Max Achivement Percent</Translate>
            </span>
          </dt>
          <dd>{incentiveSchemeEntity.maxAchivementPercent}</dd>
          <dt>
            <span id="value">
              <Translate contentKey="salesForceApp.incentiveScheme.value">Value</Translate>
            </span>
          </dt>
          <dd>{incentiveSchemeEntity.value}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="salesForceApp.incentiveScheme.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{incentiveSchemeEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="salesForceApp.incentiveScheme.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {incentiveSchemeEntity.createdAt ? (
              <TextFormat value={incentiveSchemeEntity.createdAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="salesForceApp.incentiveScheme.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {incentiveSchemeEntity.updatedAt ? (
              <TextFormat value={incentiveSchemeEntity.updatedAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="salesForceApp.incentiveScheme.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{incentiveSchemeEntity.createdBy}</dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="salesForceApp.incentiveScheme.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{incentiveSchemeEntity.updatedBy}</dd>
          <dt>
            <Translate contentKey="salesForceApp.incentiveScheme.target">Target</Translate>
          </dt>
          <dd>{incentiveSchemeEntity.target ? incentiveSchemeEntity.target.id : ''}</dd>
          <dt>
            <Translate contentKey="salesForceApp.incentiveScheme.approvedBy">Approved By</Translate>
          </dt>
          <dd>{incentiveSchemeEntity.approvedBy ? incentiveSchemeEntity.approvedBy.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/incentive-scheme" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/incentive-scheme/${incentiveSchemeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default IncentiveSchemeDetail;
