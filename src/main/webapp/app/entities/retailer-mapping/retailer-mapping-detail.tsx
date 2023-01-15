import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './retailer-mapping.reducer';

export const RetailerMappingDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const retailerMappingEntity = useAppSelector(state => state.retailerMapping.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="retailerMappingDetailsHeading">
          <Translate contentKey="salesForceApp.retailerMapping.detail.title">RetailerMapping</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{retailerMappingEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="salesForceApp.retailerMapping.name">Name</Translate>
            </span>
          </dt>
          <dd>{retailerMappingEntity.name}</dd>
          <dt>
            <span id="prmId">
              <Translate contentKey="salesForceApp.retailerMapping.prmId">Prm Id</Translate>
            </span>
          </dt>
          <dd>{retailerMappingEntity.prmId}</dd>
          <dt>
            <span id="agentCode">
              <Translate contentKey="salesForceApp.retailerMapping.agentCode">Agent Code</Translate>
            </span>
          </dt>
          <dd>{retailerMappingEntity.agentCode}</dd>
          <dt>
            <span id="pincode">
              <Translate contentKey="salesForceApp.retailerMapping.pincode">Pincode</Translate>
            </span>
          </dt>
          <dd>{retailerMappingEntity.pincode}</dd>
          <dt>
            <span id="state">
              <Translate contentKey="salesForceApp.retailerMapping.state">State</Translate>
            </span>
          </dt>
          <dd>{retailerMappingEntity.state}</dd>
          <dt>
            <span id="registedOn">
              <Translate contentKey="salesForceApp.retailerMapping.registedOn">Registed On</Translate>
            </span>
          </dt>
          <dd>
            {retailerMappingEntity.registedOn ? (
              <TextFormat value={retailerMappingEntity.registedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="mappedOn">
              <Translate contentKey="salesForceApp.retailerMapping.mappedOn">Mapped On</Translate>
            </span>
          </dt>
          <dd>
            {retailerMappingEntity.mappedOn ? (
              <TextFormat value={retailerMappingEntity.mappedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="mappedTill">
              <Translate contentKey="salesForceApp.retailerMapping.mappedTill">Mapped Till</Translate>
            </span>
          </dt>
          <dd>
            {retailerMappingEntity.mappedTill ? (
              <TextFormat value={retailerMappingEntity.mappedTill} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="salesForceApp.retailerMapping.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{retailerMappingEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="salesForceApp.retailerMapping.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {retailerMappingEntity.createdAt ? (
              <TextFormat value={retailerMappingEntity.createdAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="salesForceApp.retailerMapping.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {retailerMappingEntity.updatedAt ? (
              <TextFormat value={retailerMappingEntity.updatedAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="salesForceApp.retailerMapping.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{retailerMappingEntity.createdBy}</dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="salesForceApp.retailerMapping.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{retailerMappingEntity.updatedBy}</dd>
          <dt>
            <Translate contentKey="salesForceApp.retailerMapping.relationshipManager">Relationship Manager</Translate>
          </dt>
          <dd>{retailerMappingEntity.relationshipManager ? retailerMappingEntity.relationshipManager.id : ''}</dd>
          <dt>
            <Translate contentKey="salesForceApp.retailerMapping.approvedBy">Approved By</Translate>
          </dt>
          <dd>{retailerMappingEntity.approvedBy ? retailerMappingEntity.approvedBy.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/retailer-mapping" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/retailer-mapping/${retailerMappingEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default RetailerMappingDetail;
