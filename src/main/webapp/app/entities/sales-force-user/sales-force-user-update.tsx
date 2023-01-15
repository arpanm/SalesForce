import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getSalesForceUsers } from 'app/entities/sales-force-user/sales-force-user.reducer';
import { ISalesForceUser } from 'app/shared/model/sales-force-user.model';
import { Role } from 'app/shared/model/enumerations/role.model';
import { getEntity, updateEntity, createEntity, reset } from './sales-force-user.reducer';

export const SalesForceUserUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const salesForceUsers = useAppSelector(state => state.salesForceUser.entities);
  const salesForceUserEntity = useAppSelector(state => state.salesForceUser.entity);
  const loading = useAppSelector(state => state.salesForceUser.loading);
  const updating = useAppSelector(state => state.salesForceUser.updating);
  const updateSuccess = useAppSelector(state => state.salesForceUser.updateSuccess);
  const roleValues = Object.keys(Role);

  const handleClose = () => {
    navigate('/sales-force-user');
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
      ...salesForceUserEntity,
      ...values,
      manager: salesForceUsers.find(it => it.id.toString() === values.manager.toString()),
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
          userRole: 'SuperAdmin',
          ...salesForceUserEntity,
          manager: salesForceUserEntity?.manager?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="salesForceApp.salesForceUser.home.createOrEditLabel" data-cy="SalesForceUserCreateUpdateHeading">
            <Translate contentKey="salesForceApp.salesForceUser.home.createOrEditLabel">Create or edit a SalesForceUser</Translate>
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
                  id="sales-force-user-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('salesForceApp.salesForceUser.name')}
                id="sales-force-user-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('salesForceApp.salesForceUser.email')}
                id="sales-force-user-email"
                name="email"
                data-cy="email"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  pattern: {
                    value: /^[^@\s]+@ril\.[^@\s]+$/,
                    message: translate('entity.validation.pattern', { pattern: '^[^@\\s]+@ril\\.[^@\\s]+$' }),
                  },
                }}
              />
              <ValidatedField
                label={translate('salesForceApp.salesForceUser.phone')}
                id="sales-force-user-phone"
                name="phone"
                data-cy="phone"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  min: { value: 1000000000, message: translate('entity.validation.min', { min: 1000000000 }) },
                  max: { value: 9999999999, message: translate('entity.validation.max', { max: 9999999999 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('salesForceApp.salesForceUser.userRole')}
                id="sales-force-user-userRole"
                name="userRole"
                data-cy="userRole"
                type="select"
              >
                {roleValues.map(role => (
                  <option value={role} key={role}>
                    {translate('salesForceApp.Role.' + role)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('salesForceApp.salesForceUser.dateOfJoining')}
                id="sales-force-user-dateOfJoining"
                name="dateOfJoining"
                data-cy="dateOfJoining"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.salesForceUser.dateOfExit')}
                id="sales-force-user-dateOfExit"
                name="dateOfExit"
                data-cy="dateOfExit"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.salesForceUser.isActive')}
                id="sales-force-user-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('salesForceApp.salesForceUser.createdAt')}
                id="sales-force-user-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.salesForceUser.updatedAt')}
                id="sales-force-user-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.salesForceUser.createdBy')}
                id="sales-force-user-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.salesForceUser.updatedBy')}
                id="sales-force-user-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                id="sales-force-user-manager"
                name="manager"
                data-cy="manager"
                label={translate('salesForceApp.salesForceUser.manager')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/sales-force-user" replace color="info">
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

export default SalesForceUserUpdate;
