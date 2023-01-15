import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ISalesForceUser } from 'app/shared/model/sales-force-user.model';
import { getEntities as getSalesForceUsers } from 'app/entities/sales-force-user/sales-force-user.reducer';
import { IAchivementCache } from 'app/shared/model/achivement-cache.model';
import { Category } from 'app/shared/model/enumerations/category.model';
import { getEntity, updateEntity, createEntity, reset } from './achivement-cache.reducer';

export const AchivementCacheUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const salesForceUsers = useAppSelector(state => state.salesForceUser.entities);
  const achivementCacheEntity = useAppSelector(state => state.achivementCache.entity);
  const loading = useAppSelector(state => state.achivementCache.loading);
  const updating = useAppSelector(state => state.achivementCache.updating);
  const updateSuccess = useAppSelector(state => state.achivementCache.updateSuccess);
  const categoryValues = Object.keys(Category);

  const handleClose = () => {
    navigate('/achivement-cache');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(id));
    }

    dispatch(getSalesForceUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...achivementCacheEntity,
      ...values,
      salesAgent: salesForceUsers.find(it => it.id.toString() === values.salesAgent.toString()),
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
          category: 'CD',
          ...achivementCacheEntity,
          salesAgent: achivementCacheEntity?.salesAgent?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="salesForceApp.achivementCache.home.createOrEditLabel" data-cy="AchivementCacheCreateUpdateHeading">
            <Translate contentKey="salesForceApp.achivementCache.home.createOrEditLabel">Create or edit a AchivementCache</Translate>
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
                  id="achivement-cache-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('salesForceApp.achivementCache.startDate')}
                id="achivement-cache-startDate"
                name="startDate"
                data-cy="startDate"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.achivementCache.endDate')}
                id="achivement-cache-endDate"
                name="endDate"
                data-cy="endDate"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.achivementCache.articleId')}
                id="achivement-cache-articleId"
                name="articleId"
                data-cy="articleId"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.achivementCache.category')}
                id="achivement-cache-category"
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
                label={translate('salesForceApp.achivementCache.brand')}
                id="achivement-cache-brand"
                name="brand"
                data-cy="brand"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.achivementCache.model')}
                id="achivement-cache-model"
                name="model"
                data-cy="model"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.achivementCache.retailerId')}
                id="achivement-cache-retailerId"
                name="retailerId"
                data-cy="retailerId"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.achivementCache.retailerState')}
                id="achivement-cache-retailerState"
                name="retailerState"
                data-cy="retailerState"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.achivementCache.orderQty')}
                id="achivement-cache-orderQty"
                name="orderQty"
                data-cy="orderQty"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.achivementCache.totalOrderValue')}
                id="achivement-cache-totalOrderValue"
                name="totalOrderValue"
                data-cy="totalOrderValue"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.achivementCache.isActive')}
                id="achivement-cache-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('salesForceApp.achivementCache.createdAt')}
                id="achivement-cache-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.achivementCache.updatedAt')}
                id="achivement-cache-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.achivementCache.createdBy')}
                id="achivement-cache-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.achivementCache.updatedBy')}
                id="achivement-cache-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                id="achivement-cache-salesAgent"
                name="salesAgent"
                data-cy="salesAgent"
                label={translate('salesForceApp.achivementCache.salesAgent')}
                type="select"
              >
                <option value="" key="0" />
                {salesForceUsers
                  ? salesForceUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/achivement-cache" replace color="info">
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

export default AchivementCacheUpdate;
