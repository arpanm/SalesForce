import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IOrderCache } from 'app/shared/model/order-cache.model';
import { OrderStatus } from 'app/shared/model/enumerations/order-status.model';
import { Category } from 'app/shared/model/enumerations/category.model';
import { getEntity, updateEntity, createEntity, reset } from './order-cache.reducer';

export const OrderCacheUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const orderCacheEntity = useAppSelector(state => state.orderCache.entity);
  const loading = useAppSelector(state => state.orderCache.loading);
  const updating = useAppSelector(state => state.orderCache.updating);
  const updateSuccess = useAppSelector(state => state.orderCache.updateSuccess);
  const orderStatusValues = Object.keys(OrderStatus);
  const categoryValues = Object.keys(Category);

  const handleClose = () => {
    navigate('/order-cache');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...orderCacheEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          orderStatus: 'Open',
          category: 'CD',
          ...orderCacheEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="salesForceApp.orderCache.home.createOrEditLabel" data-cy="OrderCacheCreateUpdateHeading">
            <Translate contentKey="salesForceApp.orderCache.home.createOrEditLabel">Create or edit a OrderCache</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="order-cache-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('salesForceApp.orderCache.orderId')}
                id="order-cache-orderId"
                name="orderId"
                data-cy="orderId"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.orderCache.orderStatus')}
                id="order-cache-orderStatus"
                name="orderStatus"
                data-cy="orderStatus"
                type="select"
              >
                {orderStatusValues.map(orderStatus => (
                  <option value={orderStatus} key={orderStatus}>
                    {translate('salesForceApp.OrderStatus.' + orderStatus)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('salesForceApp.orderCache.orderDate')}
                id="order-cache-orderDate"
                name="orderDate"
                data-cy="orderDate"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.orderCache.articleId')}
                id="order-cache-articleId"
                name="articleId"
                data-cy="articleId"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.orderCache.category')}
                id="order-cache-category"
                name="category"
                data-cy="category"
                type="select"
              >
                {categoryValues.map(category => (
                  <option value={category} key={category}>
                    {translate('salesForceApp.Category.' + category)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('salesForceApp.orderCache.brand')}
                id="order-cache-brand"
                name="brand"
                data-cy="brand"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.orderCache.model')}
                id="order-cache-model"
                name="model"
                data-cy="model"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.orderCache.articlePrice')}
                id="order-cache-articlePrice"
                name="articlePrice"
                data-cy="articlePrice"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.orderCache.articleQty')}
                id="order-cache-articleQty"
                name="articleQty"
                data-cy="articleQty"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.orderCache.retailerId')}
                id="order-cache-retailerId"
                name="retailerId"
                data-cy="retailerId"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.orderCache.retailerState')}
                id="order-cache-retailerState"
                name="retailerState"
                data-cy="retailerState"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.orderCache.isActive')}
                id="order-cache-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('salesForceApp.orderCache.createdAt')}
                id="order-cache-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.orderCache.updatedAt')}
                id="order-cache-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.orderCache.createdBy')}
                id="order-cache-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.orderCache.updatedBy')}
                id="order-cache-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/order-cache" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default OrderCacheUpdate;
