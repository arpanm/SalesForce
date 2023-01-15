import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './sales-force-user.reducer';

export const SalesForceUserDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const salesForceUserEntity = useAppSelector(state => state.salesForceUser.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="salesForceUserDetailsHeading">
          <Translate contentKey="salesForceApp.salesForceUser.detail.title">SalesForceUser</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{salesForceUserEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="salesForceApp.salesForceUser.name">Name</Translate>
            </span>
          </dt>
          <dd>{salesForceUserEntity.name}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="salesForceApp.salesForceUser.email">Email</Translate>
            </span>
          </dt>
          <dd>{salesForceUserEntity.email}</dd>
          <dt>
            <span id="phone">
              <Translate contentKey="salesForceApp.salesForceUser.phone">Phone</Translate>
            </span>
          </dt>
          <dd>{salesForceUserEntity.phone}</dd>
          <dt>
            <span id="userRole">
              <Translate contentKey="salesForceApp.salesForceUser.userRole">User Role</Translate>
            </span>
          </dt>
          <dd>{salesForceUserEntity.userRole}</dd>
          <dt>
            <span id="dateOfJoining">
              <Translate contentKey="salesForceApp.salesForceUser.dateOfJoining">Date Of Joining</Translate>
            </span>
          </dt>
          <dd>
            {salesForceUserEntity.dateOfJoining ? (
              <TextFormat value={salesForceUserEntity.dateOfJoining} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="dateOfExit">
              <Translate contentKey="salesForceApp.salesForceUser.dateOfExit">Date Of Exit</Translate>
            </span>
          </dt>
          <dd>
            {salesForceUserEntity.dateOfExit ? (
              <TextFormat value={salesForceUserEntity.dateOfExit} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="salesForceApp.salesForceUser.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{salesForceUserEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="salesForceApp.salesForceUser.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {salesForceUserEntity.createdAt ? (
              <TextFormat value={salesForceUserEntity.createdAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="salesForceApp.salesForceUser.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {salesForceUserEntity.updatedAt ? (
              <TextFormat value={salesForceUserEntity.updatedAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="salesForceApp.salesForceUser.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{salesForceUserEntity.createdBy}</dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="salesForceApp.salesForceUser.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{salesForceUserEntity.updatedBy}</dd>
          <dt>
            <Translate contentKey="salesForceApp.salesForceUser.manager">Manager</Translate>
          </dt>
          <dd>{salesForceUserEntity.manager ? salesForceUserEntity.manager.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/sales-force-user" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/sales-force-user/${salesForceUserEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default SalesForceUserDetail;
