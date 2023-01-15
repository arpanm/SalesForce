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

import { IOrderCache } from 'app/shared/model/order-cache.model';
import { getEntities, reset } from './order-cache.reducer';

export const OrderCache = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );
  const [sorting, setSorting] = useState(false);

  const orderCacheList = useAppSelector(state => state.orderCache.entities);
  const loading = useAppSelector(state => state.orderCache.loading);
  const totalItems = useAppSelector(state => state.orderCache.totalItems);
  const links = useAppSelector(state => state.orderCache.links);
  const entity = useAppSelector(state => state.orderCache.entity);
  const updateSuccess = useAppSelector(state => state.orderCache.updateSuccess);

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
      <h2 id="order-cache-heading" data-cy="OrderCacheHeading">
        <Translate contentKey="salesForceApp.orderCache.home.title">Order Caches</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="salesForceApp.orderCache.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/order-cache/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="salesForceApp.orderCache.home.createLabel">Create new Order Cache</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        <InfiniteScroll
          dataLength={orderCacheList ? orderCacheList.length : 0}
          next={handleLoadMore}
          hasMore={paginationState.activePage - 1 < links.next}
          loader={<div className="loader">Loading ...</div>}
        >
          {orderCacheList && orderCacheList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('id')}>
                    <Translate contentKey="salesForceApp.orderCache.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('orderId')}>
                    <Translate contentKey="salesForceApp.orderCache.orderId">Order Id</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('orderStatus')}>
                    <Translate contentKey="salesForceApp.orderCache.orderStatus">Order Status</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('orderDate')}>
                    <Translate contentKey="salesForceApp.orderCache.orderDate">Order Date</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('articleId')}>
                    <Translate contentKey="salesForceApp.orderCache.articleId">Article Id</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('category')}>
                    <Translate contentKey="salesForceApp.orderCache.category">Category</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('brand')}>
                    <Translate contentKey="salesForceApp.orderCache.brand">Brand</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('model')}>
                    <Translate contentKey="salesForceApp.orderCache.model">Model</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('articlePrice')}>
                    <Translate contentKey="salesForceApp.orderCache.articlePrice">Article Price</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('articleQty')}>
                    <Translate contentKey="salesForceApp.orderCache.articleQty">Article Qty</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('retailerId')}>
                    <Translate contentKey="salesForceApp.orderCache.retailerId">Retailer Id</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('retailerState')}>
                    <Translate contentKey="salesForceApp.orderCache.retailerState">Retailer State</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('isActive')}>
                    <Translate contentKey="salesForceApp.orderCache.isActive">Is Active</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('createdAt')}>
                    <Translate contentKey="salesForceApp.orderCache.createdAt">Created At</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('updatedAt')}>
                    <Translate contentKey="salesForceApp.orderCache.updatedAt">Updated At</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('createdBy')}>
                    <Translate contentKey="salesForceApp.orderCache.createdBy">Created By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('updatedBy')}>
                    <Translate contentKey="salesForceApp.orderCache.updatedBy">Updated By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {orderCacheList.map((orderCache, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>
                      <Button tag={Link} to={`/order-cache/${orderCache.id}`} color="link" size="sm">
                        {orderCache.id}
                      </Button>
                    </td>
                    <td>{orderCache.orderId}</td>
                    <td>
                      <Translate contentKey={`salesForceApp.OrderStatus.${orderCache.orderStatus}`} />
                    </td>
                    <td>
                      {orderCache.orderDate ? <TextFormat type="date" value={orderCache.orderDate} format={APP_LOCAL_DATE_FORMAT} /> : null}
                    </td>
                    <td>{orderCache.articleId}</td>
                    <td>
                      <Translate contentKey={`salesForceApp.Category.${orderCache.category}`} />
                    </td>
                    <td>{orderCache.brand}</td>
                    <td>{orderCache.model}</td>
                    <td>{orderCache.articlePrice}</td>
                    <td>{orderCache.articleQty}</td>
                    <td>{orderCache.retailerId}</td>
                    <td>{orderCache.retailerState}</td>
                    <td>{orderCache.isActive ? 'true' : 'false'}</td>
                    <td>
                      {orderCache.createdAt ? <TextFormat type="date" value={orderCache.createdAt} format={APP_LOCAL_DATE_FORMAT} /> : null}
                    </td>
                    <td>
                      {orderCache.updatedAt ? <TextFormat type="date" value={orderCache.updatedAt} format={APP_LOCAL_DATE_FORMAT} /> : null}
                    </td>
                    <td>{orderCache.createdBy}</td>
                    <td>{orderCache.updatedBy}</td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`/order-cache/${orderCache.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`/order-cache/${orderCache.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button
                          tag={Link}
                          to={`/order-cache/${orderCache.id}/delete`}
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
                <Translate contentKey="salesForceApp.orderCache.home.notFound">No Order Caches found</Translate>
              </div>
            )
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

export default OrderCache;
