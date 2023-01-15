import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './sales-target-filter.reducer';

export const SalesTargetFilterDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const salesTargetFilterEntity = useAppSelector(state => state.salesTargetFilter.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="salesTargetFilterDetailsHeading">
          <Translate contentKey="salesForceApp.salesTargetFilter.detail.title">SalesTargetFilter</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{salesTargetFilterEntity.id}</dd>
          <dt>
            <span id="articleId">
              <Translate contentKey="salesForceApp.salesTargetFilter.articleId">Article Id</Translate>
            </span>
          </dt>
          <dd>{salesTargetFilterEntity.articleId}</dd>
          <dt>
            <span id="category">
              <Translate contentKey="salesForceApp.salesTargetFilter.category">Category</Translate>
            </span>
          </dt>
          <dd>{salesTargetFilterEntity.category}</dd>
          <dt>
            <span id="brand">
              <Translate contentKey="salesForceApp.salesTargetFilter.brand">Brand</Translate>
            </span>
          </dt>
          <dd>{salesTargetFilterEntity.brand}</dd>
          <dt>
            <span id="model">
              <Translate contentKey="salesForceApp.salesTargetFilter.model">Model</Translate>
            </span>
          </dt>
          <dd>{salesTargetFilterEntity.model}</dd>
          <dt>
            <span id="articleMinPrice">
              <Translate contentKey="salesForceApp.salesTargetFilter.articleMinPrice">Article Min Price</Translate>
            </span>
          </dt>
          <dd>{salesTargetFilterEntity.articleMinPrice}</dd>
          <dt>
            <span id="articleMaxPrice">
              <Translate contentKey="salesForceApp.salesTargetFilter.articleMaxPrice">Article Max Price</Translate>
            </span>
          </dt>
          <dd>{salesTargetFilterEntity.articleMaxPrice}</dd>
          <dt>
            <span id="perOrderArticleMinQty">
              <Translate contentKey="salesForceApp.salesTargetFilter.perOrderArticleMinQty">Per Order Article Min Qty</Translate>
            </span>
          </dt>
          <dd>{salesTargetFilterEntity.perOrderArticleMinQty}</dd>
          <dt>
            <span id="perOrderArticleMaxQty">
              <Translate contentKey="salesForceApp.salesTargetFilter.perOrderArticleMaxQty">Per Order Article Max Qty</Translate>
            </span>
          </dt>
          <dd>{salesTargetFilterEntity.perOrderArticleMaxQty}</dd>
          <dt>
            <span id="perRetailerArticleMinQty">
              <Translate contentKey="salesForceApp.salesTargetFilter.perRetailerArticleMinQty">Per Retailer Article Min Qty</Translate>
            </span>
          </dt>
          <dd>{salesTargetFilterEntity.perRetailerArticleMinQty}</dd>
          <dt>
            <span id="perRetailerArticleMaxQty">
              <Translate contentKey="salesForceApp.salesTargetFilter.perRetailerArticleMaxQty">Per Retailer Article Max Qty</Translate>
            </span>
          </dt>
          <dd>{salesTargetFilterEntity.perRetailerArticleMaxQty}</dd>
          <dt>
            <span id="perRetailerDailyArticleMinQty">
              <Translate contentKey="salesForceApp.salesTargetFilter.perRetailerDailyArticleMinQty">
                Per Retailer Daily Article Min Qty
              </Translate>
            </span>
          </dt>
          <dd>{salesTargetFilterEntity.perRetailerDailyArticleMinQty}</dd>
          <dt>
            <span id="perRetailerDailyArticleMaxQty">
              <Translate contentKey="salesForceApp.salesTargetFilter.perRetailerDailyArticleMaxQty">
                Per Retailer Daily Article Max Qty
              </Translate>
            </span>
          </dt>
          <dd>{salesTargetFilterEntity.perRetailerDailyArticleMaxQty}</dd>
          <dt>
            <span id="state">
              <Translate contentKey="salesForceApp.salesTargetFilter.state">State</Translate>
            </span>
          </dt>
          <dd>{salesTargetFilterEntity.state}</dd>
          <dt>
            <span id="rigion">
              <Translate contentKey="salesForceApp.salesTargetFilter.rigion">Rigion</Translate>
            </span>
          </dt>
          <dd>{salesTargetFilterEntity.rigion}</dd>
          <dt>
            <span id="join">
              <Translate contentKey="salesForceApp.salesTargetFilter.join">Join</Translate>
            </span>
          </dt>
          <dd>{salesTargetFilterEntity.join}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="salesForceApp.salesTargetFilter.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{salesTargetFilterEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="salesForceApp.salesTargetFilter.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {salesTargetFilterEntity.createdAt ? (
              <TextFormat value={salesTargetFilterEntity.createdAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="salesForceApp.salesTargetFilter.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {salesTargetFilterEntity.updatedAt ? (
              <TextFormat value={salesTargetFilterEntity.updatedAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="salesForceApp.salesTargetFilter.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{salesTargetFilterEntity.createdBy}</dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="salesForceApp.salesTargetFilter.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{salesTargetFilterEntity.updatedBy}</dd>
          <dt>
            <Translate contentKey="salesForceApp.salesTargetFilter.target">Target</Translate>
          </dt>
          <dd>{salesTargetFilterEntity.target ? salesTargetFilterEntity.target.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/sales-target-filter" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/sales-target-filter/${salesTargetFilterEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default SalesTargetFilterDetail;
