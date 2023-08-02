import React, { useEffect } from 'react';
import './App.scss';
import {Card, Grid, Button, Typography, Switch} from "@mui/material";
import axios from 'axios';
import { HashRouter, Route, Routes, useLocation, useSearchParams } from 'react-router-dom';
import { ROUTES } from './routes/routes';

function App() {

  // const [searchParams, setSearchParams] = useSearchParams();

  return (
    <HashRouter basename='/'>
      <Routes>
        {ROUTES.map((route, index) => {
          return(
            <Route
              key={index} 
              path={route.path}
              Component={route.component}
            >

            </Route>
          )
        })}
      </Routes>
    </HashRouter>
  );
}

const spotifyLogin = async () => {

  const searchParams = new URLSearchParams()

  searchParams.append("response_type","code")
  searchParams.append("scope", "user-read-private")
  searchParams.append("redirect_uri", process.env.REACT_APP_REDIRECT_URL as string)
  searchParams.append("client_id", process.env.REACT_APP_SPOTIFY_CLIENT_ID as string)

  window.location.replace("https://accounts.spotify.com/authorize?" + searchParams.toString())

}

export default App;
