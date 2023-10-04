import { Button, Card, Grid, Typography } from '@mui/material';
import axios from 'axios';
import React, { FC, useEffect, useState } from 'react';
import { SpotifyAccesResponse } from '../../models/SpotifyAccessResponse';
import { SpotifyPlaylistResponse } from '../../models/SpotifyPlaylistResponse';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSpotify, faYoutube } from '@fortawesome/free-brands-svg-icons';
import { faMusic } from '@fortawesome/free-solid-svg-icons';
import { GoogleLogin, GoogleOAuthProvider } from '@react-oauth/google';
import { useAtom } from 'jotai';
import { converterStateAtom } from '../../atoms/atoms';

interface HomeProps {}

const Home: FC<HomeProps> = () => {
  
  const [isSpotifyLoggedIn, setSpotifyLoggedIn] = useState(false)
  const [spotifyPlaylist, setSpotifyPlaylist] = useState<SpotifyPlaylistResponse>()
  const [converterState, setConverterState] = useAtom(converterStateAtom)

  useEffect(() => {
    const queryParam = new URLSearchParams(document.location.search)
    let spotifyLoggedIn = queryParam.get("code") != null
    if(spotifyLoggedIn){
      const getToken = async () => {
        let response = await axios.post( process.env.REACT_APP_SPOTIFY_ACCOUNT_URL + "api/token", {
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

  const spotifyLogin = () => {
    debugger
    let queryParams = new URLSearchParams()
    queryParams.set("response_type","code")
    queryParams.set("client_id",process.env.REACT_APP_SPOTIFY_CLIENT_ID as string)
    queryParams.set("scope","playlist-read-private")
    queryParams.set("redirect_uri", process.env.REACT_APP_REDIRECT_URL as string)
  
    window.location.replace( process.env.REACT_APP_SPOTIFY_ACCOUNT_URL+ "authorize?" + queryParams.toString())

    
  }

  const getSpotifyPlaylist = async (setSpotifyPlaylist:any) => {
    let response = await axios.get( process.env.REACT_APP_SPOTIFY_API_URL + "v1/me/playlists", {
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

  return(
    <Grid container spacing={5} 
          className='padding2030' 
          justifyContent={'center'} 
          alignItems={'center'} sx={{maxWidth:'1440PX', margin:'auto'}}>
      <Grid item xs={12} style={{textAlign:'center'}} className='padding2030'>
        <Typography variant='h3'>Transfer playlist</Typography>
        <Typography variant='subtitle2'>Transfer all playlist tracks from Spotify to YouTube</Typography>
      </Grid>
      <Grid item xs={4}>
        <Card className='shadow' style={{textAlign:'center', transition:'all 0.2s ease-in-out'}}>
          <header style={{background:'black', color:'#f5f5f5'}} className='padding2030'>
            <Typography variant='h4' style={{display:'inline-block', marginRight:'0.75rem', transition:'all 0.2s ease-in-out'}}>
              <FontAwesomeIcon icon={faSpotify} style={{marginRight:'0.25rem'}}></FontAwesomeIcon>
              Spotify
            </Typography>
            {isSpotifyLoggedIn ? "" : <Button variant='contained' style={{marginTop:'-0.75rem'}} onClick={spotifyLogin}>Login</Button>}
          </header>
          
          {isSpotifyLoggedIn ?
            <div className='padding2030' style={{background:'#f5f5f5'}}>
              <Card style={{textAlign:'left', padding:'10px 15px'}}>
                <Grid container>
                  <Grid item xs={3} style={{borderRight:'1px solid red'}}>
                    <FontAwesomeIcon icon={faMusic} style={{fontSize:'3rem', marginTop:'10px', marginLeft:'8px'}}></FontAwesomeIcon>
                  </Grid>
                  <Grid item xs={9} style={{padding:'0.5rem'}}>
                    <Typography variant='h5'>{spotifyPlaylist?.name}</Typography>
                    <Typography variant='subtitle1' style={{color:'#999'}}>Total tracks: {spotifyPlaylist?.tracks}</Typography>
                  </Grid>
                </Grid>
                
              </Card>

              <Typography style={{marginTop:'0.5rem', textAlign:'left'}} variant='subtitle2'>Transfering playlist will create new playlist on destination platform.</Typography>
              <div style={{textAlign:'right', marginTop:'1rem'}}>
                <Button variant='contained'> Transfer</Button>
              </div>
            </div>
          : ""}

          
        </Card>
      </Grid>
      <Grid item xs={4}>
        <Card className='shadow' style={{textAlign:'center'}}>
          <header style={{background:'black', color:'#f5f5f5'}} className='padding2030'>
            <Typography variant='h4' style={{display:'inline-block', marginRight:'0.75rem', transition:'all 0.2s ease-in-out'}}>
            <FontAwesomeIcon icon={faYoutube} style={{color:'#FF0100', marginRight:'0.25rem'}}></FontAwesomeIcon>
              YouTube
            </Typography>
            <div style={{width:'fit-content', margin:'auto'}}>              
              <GoogleOAuthProvider                        
                clientId={`${process.env.REACT_APP_GOOGLE_CLIENT_ID}`}>
                <GoogleLogin                
                  onSuccess={(res) => {
                    debugger
                    setConverterState((prevState) => ({
                      ...prevState,
                      googleToken: res.credential !== undefined ? res.credential : ""
                    }))
                  }} 
                  onError={() => {

                  }} />               
              </GoogleOAuthProvider>          
            </div>
          </header>
          
        </Card>
      </Grid>
    </Grid>
  )
}





export default Home;
