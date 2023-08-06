import { Button, Card, Grid, Typography } from '@mui/material';
import axios from 'axios';
import React, { FC, useEffect, useState } from 'react';
import { Buffer } from "buffer";
import { SpotifyAccesResponse } from '../../models/SpotifyAccessResponse';
import { SpotifyPlaylistResponse } from '../../models/SpotifyPlaylistResponse';

interface HomeProps {}

const Home: FC<HomeProps> = () => {
  const queryParam = new URLSearchParams(document.location.search)
  const [isSpotifyLoggedIn, setSpotifyLoggedIn] = useState(false)
  const [spotifyPlaylist, setSpotifyPlaylist] = useState<SpotifyPlaylistResponse>()

  useEffect(() => {
    let spotifyLoggedIn = queryParam.get("code") != null
    if(spotifyLoggedIn){
      const getToken = async () => {
        let response = await axios.post( process.env.REACT_APP_SPOTIFY_URL + "api/token", {
          grant_type: 'authorization_code',
          code: queryParam.get("code"),
          redirect_uri: process.env.REACT_APP_REDIRECT_URL
        }, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Authorization': 'Basic ' + Buffer.from(process.env.REACT_APP_SPOTIFY_CLIENT_ID + ':' + process.env.REACT_APP_SPOTIFY_CLEINT_SECRET).toString('base64')
          }
        })

        if(response.status == 200){
          setSpotifyLoggedIn(true)
          let responseData: SpotifyAccesResponse = response.data
          sessionStorage.setItem("spotifyAccessToken", responseData.access_token)

          getSpotifyPlaylist(setSpotifyPlaylist)
        }
      }

      getToken().catch(err => {
        console.log(err)
      })
    }
  },[])
  return(
    <Grid container spacing={2} 
          className='padding2030' 
          justifyContent={'center'} 
          alignItems={'center'}>
      <Grid item xs={4}>
        <Card className='shadow padding2030' style={{textAlign:'center'}}>
          <Typography variant='h4'>Spotify</Typography>
          <div style={{padding:'10px 15px'}}>
            {isSpotifyLoggedIn ? "LoggedIn" : <Button variant='contained' onClick={spotifyLogin}>Login</Button>}
          </div>
          <div style={{border:'1px solid'}} className='padding2030'>
            <h4>{spotifyPlaylist?.name}</h4>
            <span>{spotifyPlaylist?.tracks}</span>
            <span>{spotifyPlaylist?.href}</span>
          </div>
        </Card>
      </Grid>
    </Grid>
  )
}

const spotifyLogin = () => {
  let queryParams = new URLSearchParams()
  queryParams.set("response_type","code")
  queryParams.set("client_id",process.env.REACT_APP_SPOTIFY_CLIENT_ID as string)
  queryParams.set("scope","playlist-read-private")
  queryParams.set("redirect_uri", process.env.REACT_APP_REDIRECT_URL as string)

  window.location.replace( process.env.REACT_APP_SPOTIFY_URL+ "authorize?" + queryParams.toString())
}

const getSpotifyPlaylist = async (setSpotifyPlaylist:any) => {
  let response = await axios.get("https://api.spotify.com/v1/me/playlists", {
    headers: {
      "Authorization": "Bearer " + sessionStorage.getItem("spotifyAccessToken")
    }
  })

  if(response.status == 200){

      response.data.items.forEach((playlist:any, index:number) => {
        setSpotifyPlaylist({
          name:playlist.name,
          tracks: playlist.tracks.total,
          href: playlist.tracks.href
        })  
      });
  }
    
}

export default Home;
