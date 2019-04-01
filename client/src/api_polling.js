class BasePolling {
  constructor(dispatch, period, store) {
    this.dispatch = dispatch;
    this.period = period;
    this.store = store;
    this.interval = null;
  }

  setStore(store) {
    this.store = store;
  }

  errorHandler(err) {
    switch (err.status) {
      case 418:
        // Internal error sending request
        // Ignore
        break;
      case 521:
      default:
        this.store.dispatch("apiUnavailable");
    }
  }

  startPolling() {
    if (this.interval) return;
    const run = () => {
      this.store.dispatch(this.dispatch).catch(err => this.errorHandler(err));
    };
    run();
    this.interval = setInterval(run.bind(this), this.period);
  }

  stopPolling() {
    if (this.interval === null) return;
    clearInterval(this.interval);
    this.interval = null;
  }
}

export const Auctions = new BasePolling("auctions/fetchAll", 3000);
export const Bids = new BasePolling("bids/fetchAll", 15000);
export const Dogs = new BasePolling("dogs/fetchAll", 15000);
export const Users = new BasePolling("users/fetchAll", 15000);

export function setStore(store) {
  Auctions.setStore(store);
  Bids.setStore(store);
  Dogs.setStore(store);
  Users.setStore(store);
}
