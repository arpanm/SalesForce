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

import { IAchivementCache } from 'app/shared/model/achivement-cache.model';
import { getEntities, reset } from './achivement-cache.reducer';

export const AchivementCache = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );
  const [sorting, setSorting] = useState(false);

  const achivementCacheList = useAppSelector(state => state.achivementCache.entities);
  const loading = useAppSelector(state => state.achivementCache.loading);
  const totalItems = useAppSelector(state => state.achivementCache.totalItems);
  const links = useAppSelector(state => state.achivementCache.links);
  const entity = useAppSelector(state => state.achivementCache.entity);
  const updateSuccess = useAppSelector(state => state.achivementCache.updateSuccess);

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
      <h2 id="achivement-cache-heading" data-cy="AchivementCacheHeading">
        <Translate contentKey="salesForceApp.achivementCache.home.title">Achivement Caches</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="salesForceApp.achivementCache.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/achivement-cache/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="salesForceApp.achivementCache.home.createLabel">Create new Achivement Cache</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        <InfiniteScroll
          dataLength={achivementCacheList ? achivementCacheList.length : 0}
          next={handleLoadMore}
          hasMore={paginationState.activePage - 1 < links.next}
          loader={<div className="loader">Loading ...</div>}
        >
          {achivementCacheList && achivementCacheList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('id')}>
                    <Translate contentKey="salesForceApp.achivementCache.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('startDate')}>
                    <Translate contentKey="salesForceApp.achivementCache.startDate">Start Date</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('endDate')}>
                    <Translate contentKey="salesForceApp.achivementCache.endDate">End Date</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('articleId')}>
                    <Translate contentKey="salesForceApp.achivementCache.articleId">Article Id</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('category')}>
                    <Translate contentKey="salesForceApp.achivementCache.category">Category</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('brand')}>
                    <Translate contentKey="salesForceApp.achivementCache.brand">Brand</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('model')}>
                    <Translate contentKey="salesForceApp.achivementCache.model">Model</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('retailerId')}>
                    <Translate contentKey="salesForceApp.achivementCache.retailerId">Retailer Id</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('retailerState')}>
                    <Translate contentKey="salesForceApp.achivementCache.retailerState">Retailer State</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('orderQty')}>
                    <Translate contentKey="salesForceApp.achivementCache.orderQty">Order Qty</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('totalOrderValue')}>
                    <Translate contentKey="salesForceApp.achivementCache.totalOrderValue">Total Order Value</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('isActive')}>
                    <Translate contentKey="salesForceApp.achivementCache.isActive">Is Active</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('createdAt')}>
                    <Translate contentKey="salesForceApp.achivementCache.createdAt">Created At</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('updatedAt')}>
                    <Translate contentKey="salesForceApp.achivementCache.updatedAt">Updated At</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('createdBy')}>
                    <Translate contentKey="salesForceApp.achivementCache.createdBy">Created By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('updatedBy')}>
                    <Translate contentKey="salesForceApp.achivementCache.updatedBy">Updated By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="salesForceApp.achivementCache.salesAgent">Sales Agent</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {achivementCacheList.map((achivementCache, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>
                      <Button tag={Link} to={`/achivement-cache/${achivementCache.id}`} color="link" size="sm">
                        {achivementCache.id}
                      </Button>
                    </td>
                    <td>
                      {achivementCache.startDate ? (
                        <TextFormat type="date" value={achivementCache.startDate} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>
                      {achivementCache.endDate ? (
                        <TextFormat type="date" value={achivementCache.endDate} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>{achivementCache.articleId}</td>
                    <td>
                      <Translate contentKey={`salesForceApp.Category.${achivementCache.category}`} />
                    </td>
                    <td>{achivementCache.brand}</td>
                    <td>{achivementCache.model}</td>
                    <td>{achivementCache.retailerId}</td>
                    <td>{achivementCache.retailerState}</td>
                    <td>{achivementCache.orderQty}</td>
                    <td>{achivementCache.totalOrderValue}</td>
                    <td>{achivementCache.isActive ? 'true' : 'false'}</td>
                    <td>
                      {achivementCache.createdAt ? (
                        <TextFormat type="date" value={achivementCache.createdAt} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>
                      {achivementCache.updatedAt ? (
                        <TextFormat type="date" value={achivementCache.updatedAt} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>{achivementCache.createdBy}</td>
                    <td>{achivementCache.updatedBy}</td>
                    <td>
                      {achivementCache.salesAgent ? (
                        <Link to={`/sales-force-user/${achivementCache.salesAgent.id}`}>{achivementCache.salesAgent.id}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button
                          tag={Link}
                          to={`/achivement-cache/${achivementCache.id}`}
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
                          to={`/achivement-cache/${achivementCache.id}/edit`}
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
                          to={`/achivement-cache/${achivementCache.id}/delete`}
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
                <Translate contentKey="salesForceApp.achivementCache.home.notFound">No Achivement Caches found</Translate>
              </div>
            )
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

export default AchivementCache;
