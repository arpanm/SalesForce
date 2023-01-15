import React, { useState, useEffect } from 'react';
import InfiniteScroll from 'react-infinite-scroll-component';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IRetailerMapping } from 'app/shared/model/retailer-mapping.model';
import { getEntities, reset } from './retailer-mapping.reducer';

export const RetailerMapping = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );
  const [sorting, setSorting] = useState(false);

  const retailerMappingList = useAppSelector(state => state.retailerMapping.entities);
  const loading = useAppSelector(state => state.retailerMapping.loading);
  const totalItems = useAppSelector(state => state.retailerMapping.totalItems);
  const links = useAppSelector(state => state.retailerMapping.links);
  const entity = useAppSelector(state => state.retailerMapping.entity);
  const updateSuccess = useAppSelector(state => state.retailerMapping.updateSuccess);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      })
    );
  };

  const resetAll = () => {
    dispatch(reset());
    setPaginationState({
      ...paginationState,
      activePage: 1,
    });
    dispatch(getEntities({}));
  };

  useEffect(() => {
    resetAll();
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      resetAll();
    }
  }, [updateSuccess]);

  useEffect(() => {
    getAllEntities();
  }, [paginationState.activePage]);

  const handleLoadMore = () => {
    if ((window as any).pageYOffset > 0) {
      setPaginationState({
        ...paginationState,
        activePage: paginationState.activePage + 1,
      });
    }
  };

  useEffect(() => {
    if (sorting) {
      getAllEntities();
      setSorting(false);
    }
  }, [sorting]);

  const sort = p => () => {
    dispatch(reset());
    setPaginationState({
      ...paginationState,
      activePage: 1,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
    setSorting(true);
  };

  const handleSyncList = () => {
    resetAll();
  };

  return (
    <div>
      <h2 id="retailer-mapping-heading" data-cy="RetailerMappingHeading">
        <Translate contentKey="salesForceApp.retailerMapping.home.title">Retailer Mappings</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="salesForceApp.retailerMapping.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/retailer-mapping/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="salesForceApp.retailerMapping.home.createLabel">Create new Retailer Mapping</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        <InfiniteScroll
          dataLength={retailerMappingList ? retailerMappingList.length : 0}
          next={handleLoadMore}
          hasMore={paginationState.activePage - 1 < links.next}
          loader={<div className="loader">Loading ...</div>}
        >
          {retailerMappingList && retailerMappingList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('id')}>
                    <Translate contentKey="salesForceApp.retailerMapping.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('name')}>
                    <Translate contentKey="salesForceApp.retailerMapping.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('prmId')}>
                    <Translate contentKey="salesForceApp.retailerMapping.prmId">Prm Id</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('agentCode')}>
                    <Translate contentKey="salesForceApp.retailerMapping.agentCode">Agent Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('pincode')}>
                    <Translate contentKey="salesForceApp.retailerMapping.pincode">Pincode</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('state')}>
                    <Translate contentKey="salesForceApp.retailerMapping.state">State</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('registedOn')}>
                    <Translate contentKey="salesForceApp.retailerMapping.registedOn">Registed On</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('mappedOn')}>
                    <Translate contentKey="salesForceApp.retailerMapping.mappedOn">Mapped On</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('mappedTill')}>
                    <Translate contentKey="salesForceApp.retailerMapping.mappedTill">Mapped Till</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('isActive')}>
                    <Translate contentKey="salesForceApp.retailerMapping.isActive">Is Active</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('createdAt')}>
                    <Translate contentKey="salesForceApp.retailerMapping.createdAt">Created At</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('updatedAt')}>
                    <Translate contentKey="salesForceApp.retailerMapping.updatedAt">Updated At</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('createdBy')}>
                    <Translate contentKey="salesForceApp.retailerMapping.createdBy">Created By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('updatedBy')}>
                    <Translate contentKey="salesForceApp.retailerMapping.updatedBy">Updated By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="salesForceApp.retailerMapping.relationshipManager">Relationship Manager</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="salesForceApp.retailerMapping.approvedBy">Approved By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {retailerMappingList.map((retailerMapping, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>
                      <Button tag={Link} to={`/retailer-mapping/${retailerMapping.id}`} color="link" size="sm">
                        {retailerMapping.id}
                      </Button>
                    </td>
                    <td>{retailerMapping.name}</td>
                    <td>{retailerMapping.prmId}</td>
                    <td>{retailerMapping.agentCode}</td>
                    <td>{retailerMapping.pincode}</td>
                    <td>{retailerMapping.state}</td>
                    <td>
                      {retailerMapping.registedOn ? (
                        <TextFormat type="date" value={retailerMapping.registedOn} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>
                      {retailerMapping.mappedOn ? (
                        <TextFormat type="date" value={retailerMapping.mappedOn} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>
                      {retailerMapping.mappedTill ? (
                        <TextFormat type="date" value={retailerMapping.mappedTill} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>{retailerMapping.isActive ? 'true' : 'false'}</td>
                    <td>
                      {retailerMapping.createdAt ? (
                        <TextFormat type="date" value={retailerMapping.createdAt} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>
                      {retailerMapping.updatedAt ? (
                        <TextFormat type="date" value={retailerMapping.updatedAt} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>{retailerMapping.createdBy}</td>
                    <td>{retailerMapping.updatedBy}</td>
                    <td>
                      {retailerMapping.relationshipManager ? (
                        <Link to={`/sales-force-user/${retailerMapping.relationshipManager.id}`}>
                          {retailerMapping.relationshipManager.id}
                        </Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {retailerMapping.approvedBy ? (
                        <Link to={`/sales-force-user/${retailerMapping.approvedBy.id}`}>{retailerMapping.approvedBy.id}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button
                          tag={Link}
                          to={`/retailer-mapping/${retailerMapping.id}`}
                          color="info"
                          size="sm"
                          data-cy="entityDetailsButton"
                        >
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button
                          tag={Link}
                          to={`/retailer-mapping/${retailerMapping.id}/edit`}
                          color="primary"
                          size="sm"
                          data-cy="entityEditButton"
                        >
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button
                          tag={Link}
                          to={`/retailer-mapping/${retailerMapping.id}/delete`}
                          color="danger"
                          size="sm"
                          data-cy="entityDeleteButton"
                        >
                          <FontAwesomeIcon icon="trash" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.delete">Delete</Translate>
                          </span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            !loading && (
              <div className="alert alert-warning">
                <Translate contentKey="salesForceApp.retailerMapping.home.notFound">No Retailer Mappings found</Translate>
              </div>
            )
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

export default RetailerMapping;
