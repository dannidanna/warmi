import { WarmiWebPage } from './app.po';

describe('warmi-web App', function() {
  let page: WarmiWebPage;

  beforeEach(() => {
    page = new WarmiWebPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
