import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IIncentiveScheme } from 'app/shared/model/incentive-scheme.model';
import { getEntities as getIncentiveSchemes } from 'app/entities/incentive-scheme/incentive-scheme.reducer';
import { ISalesForceUser } from 'app/shared/model/sales-force-user.model';
import { getEntities as getSalesForceUsers } from 'app/entities/sales-force-user/sales-force-user.reducer';
import { IIncentive } from 'app/shared/model/incentive.model';
import { getEntity, updateEntity, createEntity, reset } from './incentive.reducer';

export const IncentiveUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const incentiveSchemes = useAppSelector(state => state.incentiveScheme.entities);
  const salesForceUsers = useAppSelector(state => state.salesForceUser.entities);
  const incentiveEntity = useAppSelector(state => state.incentive.entity);
  const loading = useAppSelector(state => state.incentive.loading);
  const updating = useAppSelector(state => state.incentive.updating);
  const updateSuccess = useAppSelector(state => state.incentive.updateSuccess);

  const handleClose = () => {
    navigate('/incentive');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(id));
    }

    dispatch(getIncentiveSchemes({}));
    dispatch(getSalesForceUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...incentiveEntity,
      ...values,
      scheme: incentiveSchemes.find(it => it.id.toString() === values.scheme.toString()),
      salesAgent: salesForceUsers.find(it => it.id.toString() === values.salesAgent.toString()),
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
          ...incentiveEntity,
          scheme: incentiveEntity?.scheme?.id,
          salesAgent: incentiveEntity?.salesAgent?.id,
          approvedBy: incentiveEntity?.approvedBy?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="salesForceApp.incentive.home.createOrEditLabel" data-cy="IncentiveCreateUpdateHeading">
            <Translate contentKey="salesForceApp.incentive.home.createOrEditLabel">Create or edit a Incentive</Translate>
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
                  id="incentive-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('salesForceApp.incentive.achivementPercent')}
                id="incentive-achivementPercent"
                name="achivementPercent"
                data-cy="achivementPercent"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.incentive.value')}
                id="incentive-value"
                name="value"
                data-cy="value"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.incentive.isActive')}
                id="incentive-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('salesForceApp.incentive.createdAt')}
                id="incentive-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.incentive.updatedAt')}
                id="incentive-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.incentive.createdBy')}
                id="incentive-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.incentive.updatedBy')}
                id="incentive-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                id="incentive-scheme"
                name="scheme"
                data-cy="scheme"
                label={translate('salesForceApp.incentive.scheme')}
                type="select"
              >
                <option value="" key="0" />
                {incentiveSchemes
                  ? incentiveSchemes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="incentive-salesAgent"
                name="salesAgent"
                data-cy="salesAgent"
                label={translate('salesForceApp.incentive.salesAgent')}
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
              <ValidatedField
                id="incentive-approvedBy"
                name="approvedBy"
                data-cy="approvedBy"
                label={translate('salesForceApp.incentive.approvedBy')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/incentive" replace color="info">
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

export default IncentiveUpdate;
