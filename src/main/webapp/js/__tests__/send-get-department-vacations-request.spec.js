import fetchMock from "fetch-mock";
import dateParseISOSpy from 'date-fns/parseISO';
import dateFormatSpy from 'date-fns/format';
import sendGetDepartmentVacationsRequest from '../send-get-department-vacations-request';

jest.mock('date-fns/parseISO', () => {
  const original = jest.requireActual('date-fns/parseISO');
  return jest.fn(original);
});

jest.mock('date-fns/format', () => {
  const original = jest.requireActual('date-fns/format');
  return jest.fn(original);
});

describe('send-get-department-vacations-request', () => {

  beforeEach(() => {
    window.uv = {
      i18n: {
        'application.applier.applicationsOfColleagues': 'i18n:application.applier.applicationsOfColleagues',
        'application.applier.none': 'i18n:application.applier.none',
      },
    };
  });

  afterEach(() => {
    // cleanup DOM
    while (document.body.firstElementChild) {
      document.body.firstElementChild.remove();
    }
    // and reset mocks
    fetchMock.restore();
    jest.clearAllMocks();
  });

  it('does nothing when startDate end endDate are not defined', async () => {
    await sendGetDepartmentVacationsRequest('', undefined, undefined, '', '');

    expect(fetchMock.calls).toHaveLength(0);
  });

  it('does nothing when startDate is after endDate', async () => {
    const startDate = new Date(2020, 7, 31);
    const endDate = new Date(2020, 7, 19);

    await sendGetDepartmentVacationsRequest('', startDate, endDate, '', '');

    expect(fetchMock.calls).toHaveLength(0);
  });

  it('renders response', async () => {
    fetchMock.mock(`urlprefix/persons/1337/vacations?from=2020-08-16&to=2020-08-31&ofDepartmentMembers`,
      {
        vacations: [
          {
            status: 'ALLOWED',
            from: '2020-08-19',
            to: '2020-08-21',
            person: {
              niceName: 'Bruce Wayne'
            },
          }
        ]
      },
    );

    const div = document.createElement('div');
    div.setAttribute('id', 'element');
    document.body.append(div);

    const urlPrefix = 'urlprefix';
    const startDate = new Date(2020, 7, 16);
    const endDate = new Date(2020, 7, 31);
    const personId = '1337';
    const elementSelector = '#element';

    await sendGetDepartmentVacationsRequest(urlPrefix, startDate, endDate, personId, elementSelector);

    expect(dateParseISOSpy).toHaveBeenCalledWith('2020-08-19');
    expect(dateParseISOSpy).toHaveBeenCalledWith('2020-08-21');
    expect(dateFormatSpy).toHaveBeenCalledWith(new Date(2020, 7, 19), 'dd.MM.yyyy');
    expect(dateFormatSpy).toHaveBeenCalledWith(new Date(2020, 7, 21), 'dd.MM.yyyy');
    expect(div.innerHTML).toBe('i18n:application.applier.applicationsOfColleagues<br>Bruce Wayne: 19.08.2020 - 21.08.2020&nbsp;<i class="fa fa-check positive" aria-hidden="true"></i>');
  });

  it('renders empty response', async () => {
    fetchMock.mock(`urlprefix/persons/1337/vacations?from=2020-08-16&to=2020-08-31&ofDepartmentMembers`,
      {vacations: []},
    );

    const div = document.createElement('div');
    div.setAttribute('id', 'element');
    document.body.append(div);

    const urlPrefix = 'urlprefix';
    const startDate = new Date(2020, 7, 16);
    const endDate = new Date(2020, 7, 31);
    const personId = '1337';
    const elementSelector = '#element';

    await sendGetDepartmentVacationsRequest(urlPrefix, startDate, endDate, personId, elementSelector);

    expect(dateParseISOSpy).not.toHaveBeenCalled();
    expect(div.innerHTML).toBe('i18n:application.applier.applicationsOfColleagues<br>i18n:application.applier.none');
  });
});