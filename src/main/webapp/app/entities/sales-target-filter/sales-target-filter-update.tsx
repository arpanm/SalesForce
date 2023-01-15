import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ISalesTarget } from 'app/shared/model/sales-target.model';
import { getEntities as getSalesTargets } from 'app/entities/sales-target/sales-target.reducer';
import { ISalesTargetFilter } from 'app/shared/model/sales-target-filter.model';
import { Category } from 'app/shared/model/enumerations/category.model';
import { FilterJoinType } from 'app/shared/model/enumerations/filter-join-type.model';
import { getEntity, updateEntity, createEntity, reset } from './sales-target-filter.reducer';

export const SalesTargetFilterUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const salesTargets = useAppSelector(state => state.salesTarget.entities);
  const salesTargetFilterEntity = useAppSelector(state => state.salesTargetFilter.entity);
  const loading = useAppSelector(state => state.salesTargetFilter.loading);
  const updating = useAppSelector(state => state.salesTargetFilter.updating);
  const updateSuccess = useAppSelector(state => state.salesTargetFilter.updateSuccess);
  const categoryValues = Object.keys(Category);
  const filterJoinTypeValues = Object.keys(FilterJoinType);

  const handleClose = () => {
    navigate('/sales-target-filter');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(id));
    }

    dispatch(getSalesTargets({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...salesTargetFilterEntity,
      ...values,
      target: salesTargets.find(it => it.id.toString() === values.target.toString()),
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
          join: 'AND',
          ...salesTargetFilterEntity,
          target: salesTargetFilterEntity?.target?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="salesForceApp.salesTargetFilter.home.createOrEditLabel" data-cy="SalesTargetFilterCreateUpdateHeading">
            <Translate contentKey="salesForceApp.salesTargetFilter.home.createOrEditLabel">Create or edit a SalesTargetFilter</Translate>
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
                  id="sales-target-filter-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('salesForceApp.salesTargetFilter.articleId')}
                id="sales-target-filter-articleId"
                name="articleId"
                data-cy="articleId"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTargetFilter.category')}
                id="sales-target-filter-category"
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
                label={translate('salesForceApp.salesTargetFilter.brand')}
                id="sales-target-filter-brand"
                name="brand"
                data-cy="brand"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTargetFilter.model')}
                id="sales-target-filter-model"
                name="model"
                data-cy="model"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTargetFilter.articleMinPrice')}
                id="sales-target-filter-articleMinPrice"
                name="articleMinPrice"
                data-cy="articleMinPrice"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTargetFilter.articleMaxPrice')}
                id="sales-target-filter-articleMaxPrice"
                name="articleMaxPrice"
                data-cy="articleMaxPrice"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTargetFilter.perOrderArticleMinQty')}
                id="sales-target-filter-perOrderArticleMinQty"
                name="perOrderArticleMinQty"
                data-cy="perOrderArticleMinQty"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTargetFilter.perOrderArticleMaxQty')}
                id="sales-target-filter-perOrderArticleMaxQty"
                name="perOrderArticleMaxQty"
                data-cy="perOrderArticleMaxQty"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTargetFilter.perRetailerArticleMinQty')}
                id="sales-target-filter-perRetailerArticleMinQty"
                name="perRetailerArticleMinQty"
                data-cy="perRetailerArticleMinQty"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTargetFilter.perRetailerArticleMaxQty')}
                id="sales-target-filter-perRetailerArticleMaxQty"
                name="perRetailerArticleMaxQty"
                data-cy="perRetailerArticleMaxQty"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTargetFilter.perRetailerDailyArticleMinQty')}
                id="sales-target-filter-perRetailerDailyArticleMinQty"
                name="perRetailerDailyArticleMinQty"
                data-cy="perRetailerDailyArticleMinQty"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTargetFilter.perRetailerDailyArticleMaxQty')}
                id="sales-target-filter-perRetailerDailyArticleMaxQty"
                name="perRetailerDailyArticleMaxQty"
                data-cy="perRetailerDailyArticleMaxQty"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTargetFilter.state')}
                id="sales-target-filter-state"
                name="state"
                data-cy="state"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTargetFilter.join')}
                id="sales-target-filter-join"
                name="join"
                data-cy="join"
                type="select"
              >
                {filterJoinTypeValues.map(filterJoinType => (
                  <option value={filterJoinType} key={filterJoinType}>
                    {translate('salesForceApp.FilterJoinType.' + filterJoinType)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('salesForceApp.salesTargetFilter.isActive')}
                id="sales-target-filter-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTargetFilter.createdAt')}
                id="sales-target-filter-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTargetFilter.updatedAt')}
                id="sales-target-filter-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTargetFilter.createdBy')}
                id="sales-target-filter-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTargetFilter.updatedBy')}
                id="sales-target-filter-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                id="sales-target-filter-target"
                name="target"
                data-cy="target"
                label={translate('salesForceApp.salesTargetFilter.target')}
                type="select"
              >
                <option value="" key="0" />
                {salesTargets
                  ? salesTargets.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/sales-target-filter" replace color="info">
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

export default SalesTargetFilterUpdate;
