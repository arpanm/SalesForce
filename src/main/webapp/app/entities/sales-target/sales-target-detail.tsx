import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './sales-target.reducer';

export const SalesTargetDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const salesTargetEntity = useAppSelector(state => state.salesTarget.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="salesTargetDetailsHeading">
          <Translate contentKey="salesForceApp.salesTarget.detail.title">SalesTarget</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{salesTargetEntity.id}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="salesForceApp.salesTarget.type">Type</Translate>
            </span>
          </dt>
          <dd>{salesTargetEntity.type}</dd>
          <dt>
            <span id="startDate">
              <Translate contentKey="salesForceApp.salesTarget.startDate">Start Date</Translate>
            </span>
          </dt>
          <dd>
            {salesTargetEntity.startDate ? (
              <TextFormat value={salesTargetEntity.startDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="endDate">
              <Translate contentKey="salesForceApp.salesTarget.endDate">End Date</Translate>
            </span>
          </dt>
          <dd>
            {salesTargetEntity.endDate ? <TextFormat value={salesTargetEntity.endDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="targetValue">
              <Translate contentKey="salesForceApp.salesTarget.targetValue">Target Value</Translate>
            </span>
          </dt>
          <dd>{salesTargetEntity.targetValue}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="salesForceApp.salesTarget.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{salesTargetEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="salesForceApp.salesTarget.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {salesTargetEntity.createdAt ? (
              <TextFormat value={salesTargetEntity.createdAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="salesForceApp.salesTarget.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {salesTargetEntity.updatedAt ? (
              <TextFormat value={salesTargetEntity.updatedAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="salesForceApp.salesTarget.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{salesTargetEntity.createdBy}</dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="salesForceApp.salesTarget.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{salesTargetEntity.updatedBy}</dd>
          <dt>
            <Translate contentKey="salesForceApp.salesTarget.approvedBy">Approved By</Translate>
          </dt>
          <dd>{salesTargetEntity.approvedBy ? salesTargetEntity.approvedBy.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/sales-target" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/sales-target/${salesTargetEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default SalesTargetDetail;
