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

import { IIncentive } from 'app/shared/model/incentive.model';
import { getEntities, reset } from './incentive.reducer';

export const Incentive = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );
  const [sorting, setSorting] = useState(false);

  const incentiveList = useAppSelector(state => state.incentive.entities);
  const loading = useAppSelector(state => state.incentive.loading);
  const totalItems = useAppSelector(state => state.incentive.totalItems);
  const links = useAppSelector(state => state.incentive.links);
  const entity = useAppSelector(state => state.incentive.entity);
  const updateSuccess = useAppSelector(state => state.incentive.updateSuccess);

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
      <h2 id="incentive-heading" data-cy="IncentiveHeading">
        <Translate contentKey="salesForceApp.incentive.home.title">Incentives</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="salesForceApp.incentive.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/incentive/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="salesForceApp.incentive.home.createLabel">Create new Incentive</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        <InfiniteScroll
          dataLength={incentiveList ? incentiveList.length : 0}
          next={handleLoadMore}
          hasMore={paginationState.activePage - 1 < links.next}
          loader={<div className="loader">Loading ...</div>}
        >
          {incentiveList && incentiveList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('id')}>
                    <Translate contentKey="salesForceApp.incentive.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('achivementPercent')}>
                    <Translate contentKey="salesForceApp.incentive.achivementPercent">Achivement Percent</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('value')}>
                    <Translate contentKey="salesForceApp.incentive.value">Value</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('isActive')}>
                    <Translate contentKey="salesForceApp.incentive.isActive">Is Active</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('createdAt')}>
                    <Translate contentKey="salesForceApp.incentive.createdAt">Created At</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('updatedAt')}>
                    <Translate contentKey="salesForceApp.incentive.updatedAt">Updated At</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('createdBy')}>
                    <Translate contentKey="salesForceApp.incentive.createdBy">Created By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('updatedBy')}>
                    <Translate contentKey="salesForceApp.incentive.updatedBy">Updated By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="salesForceApp.incentive.scheme">Scheme</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="salesForceApp.incentive.salesAgent">Sales Agent</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="salesForceApp.incentive.approvedBy">Approved By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {incentiveList.map((incentive, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>
                      <Button tag={Link} to={`/incentive/${incentive.id}`} color="link" size="sm">
                        {incentive.id}
                      </Button>
                    </td>
                    <td>{incentive.achivementPercent}</td>
                    <td>{incentive.value}</td>
                    <td>{incentive.isActive ? 'true' : 'false'}</td>
                    <td>
                      {incentive.createdAt ? <TextFormat type="date" value={incentive.createdAt} format={APP_LOCAL_DATE_FORMAT} /> : null}
                    </td>
                    <td>
                      {incentive.updatedAt ? <TextFormat type="date" value={incentive.updatedAt} format={APP_LOCAL_DATE_FORMAT} /> : null}
                    </td>
                    <td>{incentive.createdBy}</td>
                    <td>{incentive.updatedBy}</td>
                    <td>{incentive.scheme ? <Link to={`/incentive-scheme/${incentive.scheme.id}`}>{incentive.scheme.id}</Link> : ''}</td>
                    <td>
                      {incentive.salesAgent ? (
                        <Link to={`/sales-force-user/${incentive.salesAgent.id}`}>{incentive.salesAgent.id}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {incentive.approvedBy ? (
                        <Link to={`/sales-force-user/${incentive.approvedBy.id}`}>{incentive.approvedBy.id}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`/incentive/${incentive.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`/incentive/${incentive.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`/incentive/${incentive.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
                <Translate contentKey="salesForceApp.incentive.home.notFound">No Incentives found</Translate>
              </div>
            )
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

export default Incentive;
