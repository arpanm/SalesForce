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
import { IRetailerMapping } from 'app/shared/model/retailer-mapping.model';
import { getEntity, updateEntity, createEntity, reset } from './retailer-mapping.reducer';

export const RetailerMappingUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const salesForceUsers = useAppSelector(state => state.salesForceUser.entities);
  const retailerMappingEntity = useAppSelector(state => state.retailerMapping.entity);
  const loading = useAppSelector(state => state.retailerMapping.loading);
  const updating = useAppSelector(state => state.retailerMapping.updating);
  const updateSuccess = useAppSelector(state => state.retailerMapping.updateSuccess);

  const handleClose = () => {
    navigate('/retailer-mapping');
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
      ...retailerMappingEntity,
      ...values,
      relationshipManager: salesForceUsers.find(it => it.id.toString() === values.relationshipManager.toString()),
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
          ...retailerMappingEntity,
          relationshipManager: retailerMappingEntity?.relationshipManager?.id,
          approvedBy: retailerMappingEntity?.approvedBy?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="salesForceApp.retailerMapping.home.createOrEditLabel" data-cy="RetailerMappingCreateUpdateHeading">
            <Translate contentKey="salesForceApp.retailerMapping.home.createOrEditLabel">Create or edit a RetailerMapping</Translate>
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
                  id="retailer-mapping-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('salesForceApp.retailerMapping.name')}
                id="retailer-mapping-name"
                name="name"
                data-cy="name"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.retailerMapping.prmId')}
                id="retailer-mapping-prmId"
                name="prmId"
                data-cy="prmId"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.retailerMapping.agentCode')}
                id="retailer-mapping-agentCode"
                name="agentCode"
                data-cy="agentCode"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.retailerMapping.pincode')}
                id="retailer-mapping-pincode"
                name="pincode"
                data-cy="pincode"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.retailerMapping.state')}
                id="retailer-mapping-state"
                name="state"
                data-cy="state"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.retailerMapping.region')}
                id="retailer-mapping-region"
                name="region"
                data-cy="region"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.retailerMapping.registedOn')}
                id="retailer-mapping-registedOn"
                name="registedOn"
                data-cy="registedOn"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.retailerMapping.mappedOn')}
                id="retailer-mapping-mappedOn"
                name="mappedOn"
                data-cy="mappedOn"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.retailerMapping.mappedTill')}
                id="retailer-mapping-mappedTill"
                name="mappedTill"
                data-cy="mappedTill"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.retailerMapping.isActive')}
                id="retailer-mapping-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('salesForceApp.retailerMapping.createdAt')}
                id="retailer-mapping-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.retailerMapping.updatedAt')}
                id="retailer-mapping-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="date"
              />
              <ValidatedField
                label={translate('salesForceApp.retailerMapping.createdBy')}
                id="retailer-mapping-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('salesForceApp.retailerMapping.updatedBy')}
                id="retailer-mapping-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                id="retailer-mapping-relationshipManager"
                name="relationshipManager"
                data-cy="relationshipManager"
                label={translate('salesForceApp.retailerMapping.relationshipManager')}
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
                id="retailer-mapping-approvedBy"
                name="approvedBy"
                data-cy="approvedBy"
                label={translate('salesForceApp.retailerMapping.approvedBy')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/retailer-mapping" replace color="info">
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

export default RetailerMappingUpdate;
