import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './achivement-cache.reducer';

export const AchivementCacheDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const achivementCacheEntity = useAppSelector(state => state.achivementCache.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="achivementCacheDetailsHeading">
          <Translate contentKey="salesForceApp.achivementCache.detail.title">AchivementCache</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{achivementCacheEntity.id}</dd>
          <dt>
            <span id="startDate">
              <Translate contentKey="salesForceApp.achivementCache.startDate">Start Date</Translate>
            </span>
          </dt>
          <dd>
            {achivementCacheEntity.startDate ? (
              <TextFormat value={achivementCacheEntity.startDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="endDate">
              <Translate contentKey="salesForceApp.achivementCache.endDate">End Date</Translate>
            </span>
          </dt>
          <dd>
            {achivementCacheEntity.endDate ? (
              <TextFormat value={achivementCacheEntity.endDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="articleId">
              <Translate contentKey="salesForceApp.achivementCache.articleId">Article Id</Translate>
            </span>
          </dt>
          <dd>{achivementCacheEntity.articleId}</dd>
          <dt>
            <span id="category">
              <Translate contentKey="salesForceApp.achivementCache.category">Category</Translate>
            </span>
          </dt>
          <dd>{achivementCacheEntity.category}</dd>
          <dt>
            <span id="brand">
              <Translate contentKey="salesForceApp.achivementCache.brand">Brand</Translate>
            </span>
          </dt>
          <dd>{achivementCacheEntity.brand}</dd>
          <dt>
            <span id="model">
              <Translate contentKey="salesForceApp.achivementCache.model">Model</Translate>
            </span>
          </dt>
          <dd>{achivementCacheEntity.model}</dd>
          <dt>
            <span id="retailerId">
              <Translate contentKey="salesForceApp.achivementCache.retailerId">Retailer Id</Translate>
            </span>
          </dt>
          <dd>{achivementCacheEntity.retailerId}</dd>
          <dt>
            <span id="retailerState">
              <Translate contentKey="salesForceApp.achivementCache.retailerState">Retailer State</Translate>
            </span>
          </dt>
          <dd>{achivementCacheEntity.retailerState}</dd>
          <dt>
            <span id="orderQty">
              <Translate contentKey="salesForceApp.achivementCache.orderQty">Order Qty</Translate>
            </span>
          </dt>
          <dd>{achivementCacheEntity.orderQty}</dd>
          <dt>
            <span id="totalOrderValue">
              <Translate contentKey="salesForceApp.achivementCache.totalOrderValue">Total Order Value</Translate>
            </span>
          </dt>
          <dd>{achivementCacheEntity.totalOrderValue}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="salesForceApp.achivementCache.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{achivementCacheEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="salesForceApp.achivementCache.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {achivementCacheEntity.createdAt ? (
              <TextFormat value={achivementCacheEntity.createdAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="salesForceApp.achivementCache.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {achivementCacheEntity.updatedAt ? (
              <TextFormat value={achivementCacheEntity.updatedAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="salesForceApp.achivementCache.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{achivementCacheEntity.createdBy}</dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="salesForceApp.achivementCache.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{achivementCacheEntity.updatedBy}</dd>
          <dt>
            <Translate contentKey="salesForceApp.achivementCache.salesAgent">Sales Agent</Translate>
          </dt>
          <dd>{achivementCacheEntity.salesAgent ? achivementCacheEntity.salesAgent.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/achivement-cache" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/achivement-cache/${achivementCacheEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AchivementCacheDetail;
