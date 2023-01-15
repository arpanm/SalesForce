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
import { ISalesTarget } from 'app/shared/model/sales-target.model';
import { SalesTargetType } from 'app/shared/model/enumerations/sales-target-type.model';
import { getEntity, updateEntity, createEntity, reset } from './sales-target.reducer';

export const SalesTargetUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const salesForceUsers = useAppSelector(state => state.salesForceUser.entities);
  const salesTargetEntity = useAppSelector(state => state.salesTarget.entity);
  const loading = useAppSelector(state => state.salesTarget.loading);
  const updating = useAppSelector(state => state.salesTarget.updating);
  const updateSuccess = useAppSelector(state => state.salesTarget.updateSuccess);
  const salesTargetTypeValues = Object.keys(SalesTargetType);

  const handleClose = () => {
    navigate('/sales-target');
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
      ...salesTargetEntity,
      ...values,
      approvedBy: salesForceUsers.find(it => it.id.toString() === values.approvedBy.toString()),
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
          type: 'Revenue',
          ...salesTargetEntity,
          approvedBy: salesTargetEntity?.approvedBy?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="salesForceApp.salesTarget.home.createOrEditLabel" data-cy="SalesTargetCreateUpdateHeading">
            <Translate contentKey="salesForceApp.salesTarget.home.createOrEditLabel">Create or edit a SalesTarget</Translate>
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
                  id="sales-target-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('salesForceApp.salesTarget.type')}
                id="sales-target-type"
                name="type"
                data-cy="type"
                type="select"
              >
                {salesTargetTypeValues.map(salesTargetType => (
                  <option value={salesTargetType} key={salesTargetType}>
                    {translate('salesForceApp.SalesTargetType.' + salesTargetType)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('salesForceApp.salesTarget.startDate')}
                id="sales-target-startDate"
                name="startDate"
                data-cy="startDate"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('salesForceApp.salesTarget.endDate')}
                id="sales-target-endDate"
                name="endDate"
                data-cy="endDate"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('salesForceApp.salesTarget.targetValue')}
                id="sales-target-targetValue"
                name="targetValue"
                data-cy="targetValue"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('salesForceApp.salesTarget.isActive')}
                id="sales-target-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTarget.createdAt')}
                id="sales-target-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTarget.updatedAt')}
                id="sales-target-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTarget.createdBy')}
                id="sales-target-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.salesTarget.updatedBy')}
                id="sales-target-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                id="sales-target-approvedBy"
                name="approvedBy"
                data-cy="approvedBy"
                label={translate('salesForceApp.salesTarget.approvedBy')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/sales-target" replace color="info">
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

export default SalesTargetUpdate;
