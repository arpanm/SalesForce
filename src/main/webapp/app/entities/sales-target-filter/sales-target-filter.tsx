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

import { ISalesTargetFilter } from 'app/shared/model/sales-target-filter.model';
import { getEntities, reset } from './sales-target-filter.reducer';

export const SalesTargetFilter = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );
  const [sorting, setSorting] = useState(false);

  const salesTargetFilterList = useAppSelector(state => state.salesTargetFilter.entities);
  const loading = useAppSelector(state => state.salesTargetFilter.loading);
  const totalItems = useAppSelector(state => state.salesTargetFilter.totalItems);
  const links = useAppSelector(state => state.salesTargetFilter.links);
  const entity = useAppSelector(state => state.salesTargetFilter.entity);
  const updateSuccess = useAppSelector(state => state.salesTargetFilter.updateSuccess);

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
      <h2 id="sales-target-filter-heading" data-cy="SalesTargetFilterHeading">
        <Translate contentKey="salesForceApp.salesTargetFilter.home.title">Sales Target Filters</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="salesForceApp.salesTargetFilter.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/sales-target-filter/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="salesForceApp.salesTargetFilter.home.createLabel">Create new Sales Target Filter</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        <InfiniteScroll
          dataLength={salesTargetFilterList ? salesTargetFilterList.length : 0}
          next={handleLoadMore}
          hasMore={paginationState.activePage - 1 < links.next}
          loader={<div className="loader">Loading ...</div>}
        >
          {salesTargetFilterList && salesTargetFilterList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('id')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('articleId')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.articleId">Article Id</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('category')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.category">Category</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('brand')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.brand">Brand</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('model')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.model">Model</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('articleMinPrice')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.articleMinPrice">Article Min Price</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('articleMaxPrice')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.articleMaxPrice">Article Max Price</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('perOrderArticleMinQty')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.perOrderArticleMinQty">Per Order Article Min Qty</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('perOrderArticleMaxQty')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.perOrderArticleMaxQty">Per Order Article Max Qty</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('perRetailerArticleMinQty')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.perRetailerArticleMinQty">
                      Per Retailer Article Min Qty
                    </Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('perRetailerArticleMaxQty')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.perRetailerArticleMaxQty">
                      Per Retailer Article Max Qty
                    </Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('perRetailerDailyArticleMinQty')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.perRetailerDailyArticleMinQty">
                      Per Retailer Daily Article Min Qty
                    </Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('perRetailerDailyArticleMaxQty')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.perRetailerDailyArticleMaxQty">
                      Per Retailer Daily Article Max Qty
                    </Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('state')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.state">State</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('join')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.join">Join</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('isActive')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.isActive">Is Active</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('createdAt')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.createdAt">Created At</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('updatedAt')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.updatedAt">Updated At</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('createdBy')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.createdBy">Created By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('updatedBy')}>
                    <Translate contentKey="salesForceApp.salesTargetFilter.updatedBy">Updated By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="salesForceApp.salesTargetFilter.target">Target</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {salesTargetFilterList.map((salesTargetFilter, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>
                      <Button tag={Link} to={`/sales-target-filter/${salesTargetFilter.id}`} color="link" size="sm">
                        {salesTargetFilter.id}
                      </Button>
                    </td>
                    <td>{salesTargetFilter.articleId}</td>
                    <td>
                      <Translate contentKey={`salesForceApp.Category.${salesTargetFilter.category}`} />
                    </td>
                    <td>{salesTargetFilter.brand}</td>
                    <td>{salesTargetFilter.model}</td>
                    <td>{salesTargetFilter.articleMinPrice}</td>
                    <td>{salesTargetFilter.articleMaxPrice}</td>
                    <td>{salesTargetFilter.perOrderArticleMinQty}</td>
                    <td>{salesTargetFilter.perOrderArticleMaxQty}</td>
                    <td>{salesTargetFilter.perRetailerArticleMinQty}</td>
                    <td>{salesTargetFilter.perRetailerArticleMaxQty}</td>
                    <td>{salesTargetFilter.perRetailerDailyArticleMinQty}</td>
                    <td>{salesTargetFilter.perRetailerDailyArticleMaxQty}</td>
                    <td>{salesTargetFilter.state}</td>
                    <td>
                      <Translate contentKey={`salesForceApp.FilterJoinType.${salesTargetFilter.join}`} />
                    </td>
                    <td>{salesTargetFilter.isActive ? 'true' : 'false'}</td>
                    <td>
                      {salesTargetFilter.createdAt ? (
                        <TextFormat type="date" value={salesTargetFilter.createdAt} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>
                      {salesTargetFilter.updatedAt ? (
                        <TextFormat type="date" value={salesTargetFilter.updatedAt} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>{salesTargetFilter.createdBy}</td>
                    <td>{salesTargetFilter.updatedBy}</td>
                    <td>
                      {salesTargetFilter.target ? (
                        <Link to={`/sales-target/${salesTargetFilter.target.id}`}>{salesTargetFilter.target.id}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button
                          tag={Link}
                          to={`/sales-target-filter/${salesTargetFilter.id}`}
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
                          to={`/sales-target-filter/${salesTargetFilter.id}/edit`}
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
                          to={`/sales-target-filter/${salesTargetFilter.id}/delete`}
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
                <Translate contentKey="salesForceApp.salesTargetFilter.home.notFound">No Sales Target Filters found</Translate>
              </div>
            )
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

export default SalesTargetFilter;
