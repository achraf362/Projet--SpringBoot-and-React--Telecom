import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import { TeamManagement, PlayerManagement } from './components/team_player_management_ui';

const App = () => {
    return (
        <Router>
            <div className="container mt-4">
                <h1>Team & Player Management</h1>
                <nav className="d-flex flex-column gap-3">
                    <Link to="/teams" className="btn btn-primary w-100 custom-btn">
                        <i className="bi bi-people-fill me-2"></i>
                        Manage Teams
                    </Link>
                    <Link to="/players" className="btn btn-primary w-100 custom-btn">
                        <i className="bi bi-person-fill me-2"></i>
                        Manage Players
                    </Link>
                </nav>


                <Routes>
                    <Route path="/teams" element={<TeamManagement/>}/>
                    <Route path="/players" element={<PlayerManagement/>}/>
                </Routes>
            </div>
        </Router>
    );
};

export default App;
