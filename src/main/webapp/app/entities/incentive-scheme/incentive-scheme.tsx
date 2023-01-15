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

import { IIncentiveScheme } from 'app/shared/model/incentive-scheme.model';
import { getEntities, reset } from './incentive-scheme.reducer';

export const IncentiveScheme = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );
  const [sorting, setSorting] = useState(false);

  const incentiveSchemeList = useAppSelector(state => state.incentiveScheme.entities);
  const loading = useAppSelector(state => state.incentiveScheme.loading);
  const totalItems = useAppSelector(state => state.incentiveScheme.totalItems);
  const links = useAppSelector(state => state.incentiveScheme.links);
  const entity = useAppSelector(state => state.incentiveScheme.entity);
  const updateSuccess = useAppSelector(state => state.incentiveScheme.updateSuccess);

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
      <h2 id="incentive-scheme-heading" data-cy="IncentiveSchemeHeading">
        <Translate contentKey="salesForceApp.incentiveScheme.home.title">Incentive Schemes</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="salesForceApp.incentiveScheme.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/incentive-scheme/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="salesForceApp.incentiveScheme.home.createLabel">Create new Incentive Scheme</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        <InfiniteScroll
          dataLength={incentiveSchemeList ? incentiveSchemeList.length : 0}
          next={handleLoadMore}
          hasMore={paginationState.activePage - 1 < links.next}
          loader={<div className="loader">Loading ...</div>}
        >
          {incentiveSchemeList && incentiveSchemeList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('id')}>
                    <Translate contentKey="salesForceApp.incentiveScheme.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('type')}>
                    <Translate contentKey="salesForceApp.incentiveScheme.type">Type</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('minAchivementPercent')}>
                    <Translate contentKey="salesForceApp.incentiveScheme.minAchivementPercent">Min Achivement Percent</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('maxAchivementPercent')}>
                    <Translate contentKey="salesForceApp.incentiveScheme.maxAchivementPercent">Max Achivement Percent</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('schemeValue')}>
                    <Translate contentKey="salesForceApp.incentiveScheme.schemeValue">Scheme Value</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('isActive')}>
                    <Translate contentKey="salesForceApp.incentiveScheme.isActive">Is Active</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('createdAt')}>
                    <Translate contentKey="salesForceApp.incentiveScheme.createdAt">Created At</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('updatedAt')}>
                    <Translate contentKey="salesForceApp.incentiveScheme.updatedAt">Updated At</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('createdBy')}>
                    <Translate contentKey="salesForceApp.incentiveScheme.createdBy">Created By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('updatedBy')}>
                    <Translate contentKey="salesForceApp.incentiveScheme.updatedBy">Updated By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="salesForceApp.incentiveScheme.target">Target</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="salesForceApp.incentiveScheme.approvedBy">Approved By</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {incentiveSchemeList.map((incentiveScheme, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>
                      <Button tag={Link} to={`/incentive-scheme/${incentiveScheme.id}`} color="link" size="sm">
                        {incentiveScheme.id}
                      </Button>
                    </td>
                    <td>
                      <Translate contentKey={`salesForceApp.IncentiveSchemeType.${incentiveScheme.type}`} />
                    </td>
                    <td>{incentiveScheme.minAchivementPercent}</td>
                    <td>{incentiveScheme.maxAchivementPercent}</td>
                    <td>{incentiveScheme.schemeValue}</td>
                    <td>{incentiveScheme.isActive ? 'true' : 'false'}</td>
                    <td>
                      {incentiveScheme.createdAt ? (
                        <TextFormat type="date" value={incentiveScheme.createdAt} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>
                      {incentiveScheme.updatedAt ? (
                        <TextFormat type="date" value={incentiveScheme.updatedAt} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>{incentiveScheme.createdBy}</td>
                    <td>{incentiveScheme.updatedBy}</td>
                    <td>
                      {incentiveScheme.target ? (
                        <Link to={`/sales-target/${incentiveScheme.target.id}`}>{incentiveScheme.target.id}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {incentiveScheme.approvedBy ? (
                        <Link to={`/sales-force-user/${incentiveScheme.approvedBy.id}`}>{incentiveScheme.approvedBy.id}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button
                          tag={Link}
                          to={`/incentive-scheme/${incentiveScheme.id}`}
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
                          to={`/incentive-scheme/${incentiveScheme.id}/edit`}
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
                          to={`/incentive-scheme/${incentiveScheme.id}/delete`}
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
                <Translate contentKey="salesForceApp.incentiveScheme.home.notFound">No Incentive Schemes found</Translate>
              </div>
            )
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

export default IncentiveScheme;
