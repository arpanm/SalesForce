import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './order-cache.reducer';

export const OrderCacheDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const orderCacheEntity = useAppSelector(state => state.orderCache.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="orderCacheDetailsHeading">
          <Translate contentKey="salesForceApp.orderCache.detail.title">OrderCache</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{orderCacheEntity.id}</dd>
          <dt>
            <span id="orderId">
              <Translate contentKey="salesForceApp.orderCache.orderId">Order Id</Translate>
            </span>
          </dt>
          <dd>{orderCacheEntity.orderId}</dd>
          <dt>
            <span id="orderStatus">
              <Translate contentKey="salesForceApp.orderCache.orderStatus">Order Status</Translate>
            </span>
          </dt>
          <dd>{orderCacheEntity.orderStatus}</dd>
          <dt>
            <span id="orderDate">
              <Translate contentKey="salesForceApp.orderCache.orderDate">Order Date</Translate>
            </span>
          </dt>
          <dd>
            {orderCacheEntity.orderDate ? (
              <TextFormat value={orderCacheEntity.orderDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="articleId">
              <Translate contentKey="salesForceApp.orderCache.articleId">Article Id</Translate>
            </span>
          </dt>
          <dd>{orderCacheEntity.articleId}</dd>
          <dt>
            <span id="category">
              <Translate contentKey="salesForceApp.orderCache.category">Category</Translate>
            </span>
          </dt>
          <dd>{orderCacheEntity.category}</dd>
          <dt>
            <span id="brand">
              <Translate contentKey="salesForceApp.orderCache.brand">Brand</Translate>
            </span>
          </dt>
          <dd>{orderCacheEntity.brand}</dd>
          <dt>
            <span id="model">
              <Translate contentKey="salesForceApp.orderCache.model">Model</Translate>
            </span>
          </dt>
          <dd>{orderCacheEntity.model}</dd>
          <dt>
            <span id="articlePrice">
              <Translate contentKey="salesForceApp.orderCache.articlePrice">Article Price</Translate>
            </span>
          </dt>
          <dd>{orderCacheEntity.articlePrice}</dd>
          <dt>
            <span id="articleQty">
              <Translate contentKey="salesForceApp.orderCache.articleQty">Article Qty</Translate>
            </span>
          </dt>
          <dd>{orderCacheEntity.articleQty}</dd>
          <dt>
            <span id="retailerId">
              <Translate contentKey="salesForceApp.orderCache.retailerId">Retailer Id</Translate>
            </span>
          </dt>
          <dd>{orderCacheEntity.retailerId}</dd>
          <dt>
            <span id="retailerState">
              <Translate contentKey="salesForceApp.orderCache.retailerState">Retailer State</Translate>
            </span>
          </dt>
          <dd>{orderCacheEntity.retailerState}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="salesForceApp.orderCache.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{orderCacheEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="salesForceApp.orderCache.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {orderCacheEntity.createdAt ? (
              <TextFormat value={orderCacheEntity.createdAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="salesForceApp.orderCache.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {orderCacheEntity.updatedAt ? (
              <TextFormat value={orderCacheEntity.updatedAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="salesForceApp.orderCache.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{orderCacheEntity.createdBy}</dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="salesForceApp.orderCache.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{orderCacheEntity.updatedBy}</dd>
        </dl>
        <Button tag={Link} to="/order-cache" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/order-cache/${orderCacheEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default OrderCacheDetail;
