import React from 'react';
import './App.css';
import Dashboard from './Dashboard';
// import Login from './Login';
import { BrowserRouter, } from "react-router-dom";
import { Route, Redirect, Switch } from "react-router";

function App() {
  return (
    <div className="App">

      <BrowserRouter>
        <Switch>
          
          {/* <Route path="/login" component={Login} /> */}
          <Route path="/dashboard" component={Dashboard} />
          <Route exact path="">
            <Redirect to="/dashboard" />
          </Route>
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
