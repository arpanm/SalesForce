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
import { ISalesForceUser } from 'app/shared/model/sales-force-user.model';
import { getEntities as getSalesForceUsers } from 'app/entities/sales-force-user/sales-force-user.reducer';
import { IIncentiveScheme } from 'app/shared/model/incentive-scheme.model';
import { IncentiveSchemeType } from 'app/shared/model/enumerations/incentive-scheme-type.model';
import { getEntity, updateEntity, createEntity, reset } from './incentive-scheme.reducer';

export const IncentiveSchemeUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const salesTargets = useAppSelector(state => state.salesTarget.entities);
  const salesForceUsers = useAppSelector(state => state.salesForceUser.entities);
  const incentiveSchemeEntity = useAppSelector(state => state.incentiveScheme.entity);
  const loading = useAppSelector(state => state.incentiveScheme.loading);
  const updating = useAppSelector(state => state.incentiveScheme.updating);
  const updateSuccess = useAppSelector(state => state.incentiveScheme.updateSuccess);
  const incentiveSchemeTypeValues = Object.keys(IncentiveSchemeType);

  const handleClose = () => {
    navigate('/incentive-scheme');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(id));
    }

    dispatch(getSalesTargets({}));
    dispatch(getSalesForceUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...incentiveSchemeEntity,
      ...values,
      target: salesTargets.find(it => it.id.toString() === values.target.toString()),
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
          type: 'Percent',
          ...incentiveSchemeEntity,
          target: incentiveSchemeEntity?.target?.id,
          approvedBy: incentiveSchemeEntity?.approvedBy?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="salesForceApp.incentiveScheme.home.createOrEditLabel" data-cy="IncentiveSchemeCreateUpdateHeading">
            <Translate contentKey="salesForceApp.incentiveScheme.home.createOrEditLabel">Create or edit a IncentiveScheme</Translate>
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
                  id="incentive-scheme-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('salesForceApp.incentiveScheme.type')}
                id="incentive-scheme-type"
                name="type"
                data-cy="type"
                type="select"
              >
                {incentiveSchemeTypeValues.map(incentiveSchemeType => (
                  <option value={incentiveSchemeType} key={incentiveSchemeType}>
                    {translate('salesForceApp.IncentiveSchemeType.' + incentiveSchemeType)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('salesForceApp.incentiveScheme.minAchivementPercent')}
                id="incentive-scheme-minAchivementPercent"
                name="minAchivementPercent"
                data-cy="minAchivementPercent"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.incentiveScheme.maxAchivementPercent')}
                id="incentive-scheme-maxAchivementPercent"
                name="maxAchivementPercent"
                data-cy="maxAchivementPercent"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.incentiveScheme.value')}
                id="incentive-scheme-value"
                name="value"
                data-cy="value"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.incentiveScheme.isActive')}
                id="incentive-scheme-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('salesForceApp.incentiveScheme.createdAt')}
                id="incentive-scheme-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.incentiveScheme.updatedAt')}
                id="incentive-scheme-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.incentiveScheme.createdBy')}
                id="incentive-scheme-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.incentiveScheme.updatedBy')}
                id="incentive-scheme-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                id="incentive-scheme-target"
                name="target"
                data-cy="target"
                label={translate('salesForceApp.incentiveScheme.target')}
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
              <ValidatedField
                id="incentive-scheme-approvedBy"
                name="approvedBy"
                data-cy="approvedBy"
                label={translate('salesForceApp.incentiveScheme.approvedBy')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/incentive-scheme" replace color="info">
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

export default IncentiveSchemeUpdate;
