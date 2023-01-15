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

import { ISalesTarget } from 'app/shared/model/sales-target.model';
import { getEntities, reset } from './sales-target.reducer';

export const SalesTarget = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );
  const [sorting, setSorting] = useState(false);

  const salesTargetList = useAppSelector(state => state.salesTarget.entities);
  const loading = useAppSelector(state => state.salesTarget.loading);
  const totalItems = useAppSelector(state => state.salesTarget.totalItems);
  const links = useAppSelector(state => state.salesTarget.links);
  const entity = useAppSelector(state => state.salesTarget.entity);
  const updateSuccess = useAppSelector(state => state.salesTarget.updateSuccess);

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
      <h2 id="sales-target-heading" data-cy="SalesTargetHeading">
        <Translate contentKey="salesForceApp.salesTarget.home.title">Sales Targets</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="salesForceApp.salesTarget.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/sales-target/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="salesForceApp.salesTarget.home.createLabel">Create new Sales Target</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        <InfiniteScroll
          dataLength={salesTargetList ? salesTargetList.length : 0}
          next={handleLoadMore}
          hasMore={paginationState.activePage - 1 < links.next}
          loader={<div className="loader">Loading ...</div>}
        >
          {salesTargetList && salesTargetList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('id')}>
                    <Translate contentKey="salesForceApp.salesTarget.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('type')}>
                    <Translate contentKey="salesForceApp.salesTarget.type">Type</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('startDate')}>
                    <Translate contentKey="salesForceApp.salesTarget.startDate">Start Date</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('endDate')}>
                    <Translate contentKey="salesForceApp.salesTarget.endDate">End Date</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('targetValue')}>
                    <Translate contentKey="salesForceApp.salesTarget.targetValue">Target Value</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('isActive')}>
                    <Translate contentKey="salesForceApp.salesTarget.isActive">Is Active</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('createdAt')}>
                    <Translate contentKey="salesForceApp.salesTarget.createdAt">Created At</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('updatedAt')}>
                    <Translate contentKey="salesForceApp.salesTarget.updatedAt">Updated At</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('createdBy')}>
                    <Translate contentKey="salesForceApp.salesTarget.createdBy">Created By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('updatedBy')}>
                    <Translate contentKey="salesForceApp.salesTarget.updatedBy">Updated By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="salesForceApp.salesTarget.approvedBy">Approved By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {salesTargetList.map((salesTarget, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>
                      <Button tag={Link} to={`/sales-target/${salesTarget.id}`} color="link" size="sm">
                        {salesTarget.id}
                      </Button>
                    </td>
                    <td>
                      <Translate contentKey={`salesForceApp.SalesTargetType.${salesTarget.type}`} />
                    </td>
                    <td>
                      {salesTarget.startDate ? (
                        <TextFormat type="date" value={salesTarget.startDate} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>
                      {salesTarget.endDate ? <TextFormat type="date" value={salesTarget.endDate} format={APP_LOCAL_DATE_FORMAT} /> : null}
                    </td>
                    <td>{salesTarget.targetValue}</td>
                    <td>{salesTarget.isActive ? 'true' : 'false'}</td>
                    <td>
                      {salesTarget.createdAt ? (
                        <TextFormat type="date" value={salesTarget.createdAt} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>
                      {salesTarget.updatedAt ? (
                        <TextFormat type="date" value={salesTarget.updatedAt} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>{salesTarget.createdBy}</td>
                    <td>{salesTarget.updatedBy}</td>
                    <td>
                      {salesTarget.approvedBy ? (
                        <Link to={`/sales-force-user/${salesTarget.approvedBy.id}`}>{salesTarget.approvedBy.id}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`/sales-target/${salesTarget.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`/sales-target/${salesTarget.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button
                          tag={Link}
                          to={`/sales-target/${salesTarget.id}/delete`}
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
                <Translate contentKey="salesForceApp.salesTarget.home.notFound">No Sales Targets found</Translate>
              </div>
            )
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

export default SalesTarget;
